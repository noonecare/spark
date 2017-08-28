# Spark Partition

---

重要性： partition 数决定了 map 数， 决定了并行的程度，影响了 spark 程序的性能。不同的 Partition 之间需要通过网络传递数据，network traffic 太多，会影响 spark 程序的性能。


---

# 载入数据( load data )

> - 结论：读取文件时， 如果文件采用了 unsplitable 的压缩格式（比如 gzip），那么读入的 rdd 的分区数是 1。如果文件采用了 splitable 的压缩格式（比如 bz2）或者没有压缩，那么读入的 rdd 的分区数由读入文件的 block 数决定。
> - 实验验证：找个大约 5G（这么大的文件一定会占用多个 block 的） 的文本文件(比如test.json)， 上传到 HDFS 中。 使用
```hdfs
hadoop fs -stat %o test.json
```
得出当前文件的 block size(不同文件的 block size 不一定相等)
```hdfs
hadoop fs -du test.json
```
得到当前文件的总大小。根据 blocksize 和 文件总大小，算出文件的 block 数（每个block 也称为 inputsplit）
```scala
spark-shell
>val data = sc.textFile("test.json")
>data getNumPartitions
```
实验结果是， data 的分区数刚好等于 test.json 占用的 block 数。
用 gzip 压缩 test.json
```shell
gzip -c test.json > test.json.gz
```
对于 test.json.gz 执行刚才对于 test.json 的操作。实验结果是， data 的分区数为1， 而 test.json.gz 占用的分区数不为1。



----


# rdd 获取有关 partition 的信息
- rdd.getNumPartitions 属性是 rdd 的分区数
- rdd.repartition 指定这个分区数。比如 rdd.repartition(10)。对于 ShuffledRDD ， 执行 repartition 方法之后，会失去 partitioner。ShuffledRDD 是根据key 和 partioner 进过 shuffle（重新 partition） 得到的带分区的 RDD。
- rdd.partitions 属性指明这个 rdd 的每个分区。
- partitioner 指明 rdd 是根据那个 partitoner 分区的（感觉说成 shuffled 更好），如果 rdd 不是 ShuffledRDD 那么 partitioner 属性值为空。

----



# 与 partition 有关的 transform

- 所有可能会改变 key 的 transformation 都会丢失 partitoner, 比如 map。
- 一定不会改变 key 的 transformation 会保留 partitioner。

- 对于一元的 ShuffledRDD, 转换出的 ShuffledRDD 的 partitioner 和要转换的 RDD 的 partitioner 一致，如果 key 一定不会改变的话。

- 对于二元的 RDD, 如果两个中有一个有 partitioner， 那么转换出的 ShuffledRDD 的 partitioner 和这唯一的一个 partitiner, 如果两个都有 partitioner, 转换出的 ShuffledRDD 的 partitioner 和第一个 ShuffledRDD 的 partitioner 相同。

-----


# shuffle 对性能的影响

- shuffle 操作占用 network traffic, 是性能的瓶颈。所以对于 ShuffledRDD, 特别是常用的 ShuffledRDD 都要 persist()， 这样可以减少 shuffle ， 进而提高程序的性能。

- persist ShuffledRDD 对于很多跟 Key 有关的操作都会有好处比如：
reduceByKey
groupByKey
reduceByKey
cogroup
join
leftOuterJoin
rightOuterJoin
combineByKey
lookup
groupWith

- persist ShuffledRDD 的好处具体来说：
> - 对于 一元 RDD action 操作（比如reduceByKey）, 可以利用 locally computation（combine操作， hadoop mapreduce 中也有）, 来减小network traffic。
> - 如果是两元的操作，至少使得其中一个 RDD 不用再shuffle。

---


# 自定义 partitioner

- 为什么要自定义 partitioner

- 如何定义 partitiner

> - scala 中定义 partitiner 需要继承 org.apache.spark.Partitioner 类，并且实现以下方法：
>> - numPartitions: Int, 返回 shuffledRDD 的分区数。
>> - getPartition(key: Any): Int 得到分区的id 号， id 号必须是 [0, numPartitions-1] 中的一个自然数。
>> - equals(other: Partitioner):Boolean 判断两个这个 partitioner 类型的两个 partitioner 实例是否相同。

> - python 中的 partitoner 只需要是一个返回 [0, numPartitions-1] 的函数，不需要定义 equals 因为 python 判断 a , b 函数是不是 同一个 partitioner 是根据 id(a) == id(b) 来判定的。


我这里举个例子：

```scala
import org.apache.spark.Partition
import java.Net.URL


class DomainPartitioner(partitionNum: Int) extends Partitioner{
	override def numPartitions: Int = partitionNum
    override def getPartition(key: Any) = {
    	if(key.isInstanceOf[String])
        {
    		val temp = (new java.net.URL(t)).getHost().hashCode() % partitionNum
            if( temp < 0 )
            {
            	temp + partitionNum
            }
            else
            {
            	temp
            }
    	}
    }
    override def equals(other: Any): Boolean = other match {
    	case other: DomainPartitioner => this.numPartions == other.numPartitions
        case _: Any => false
    }
```

---

# Per-Partition Operation

- 为什么要有 per-partition 的操作
> - 在同一个 partition 中共享一些资源，比如打开一个 client 端，处理 url; 比如建立一个同数据库的链接。在同一个 partition 中可以分享这些资源，如果对于每个数据都打开关闭这样一个链接，反而很浪费时间和资源。
> - 在同一个分区中共有 setup 和 tearDown 的过程。节省时间。

- 有哪些 per-partition 的操作

|function name|we are called with|we return|Function Signature on RDD[T]|
|-----|---|---||
|mapPartitions()|Iterator of the elements in that partition|Iterator of return elements|Iterator[T]=>Iterator[U]|
|mapPartitionsWithIndex()|Integer of partition number and iterator of the elemnets in that partition|Iterator of return elements|(Int, Iterator[T])=>Iterator[U]|
|foreachPartition()|Iterator of the elements in that partition|nothing|Iterator[T]=>Unit|


### [如何处理数据不平衡的问题](http://www.jasongj.com/spark/skew/)
