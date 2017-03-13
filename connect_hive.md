spark 能实现 大数据开源工具的无缝对接，那么举个例子，如何使用 spark 建立 hive 表。

可以把 spark 的 sqlContext 想象成一个 临时的 Hive, 如果合理的配置一下，这个 sqlContext 就是集群上的Hive, 你可以在 spark 中，操作 Hive 。

如何配置呢，就是读取一下 Hive-site.xml 这个hive 的配置文件。下面是 learning Spark 一书中的原话：

Finally, to connect Spark SQL to an existing Hive installation, you must copy your hive-site.xml file to Spark's configuration directory($SPARK_HOME/conf), If you don't have an existing Hive installation, Spark SQL will still run.

首先通过 复制 hive-site.xml 把 Spark SQL 链接到 hive.


你可以读入一个 DataFrame， 然后把这个 DataFrame 注册成一张临时表，
然后使用 sql 函数，建立一张表（特别的你可以like  刚才注册的临时表）。
然后使用 sql 函数，insert into table select * from 语句，把临时表中的数据，都保存在刚刚建立的表中。这样，你在 spark 内，就完成了 建立 Hive 表的操作。非常方便。

我想到的解法可行，不过网上给出更简便的语句。
create table <table name> as select * from <temp table>.

我在公司的测试集群上试过了（公司的测试集群，都是配好的，sqlContext 就是 集群上装的 Hive），上面的方法可行。

从此之后，从 json 中读取文件，写入到 Hive 表中变得非常简单。都不用写 schema 的。

但是从 Hive 到 impala, 有 impala 不支持复杂数据的问题。

我感觉这是常见的问题，但是我却没有找到简单方便的解法。


flatten_data = data.map(lambda row: )


CREATE TABLE `industry_research.jd_hotel`(
  `all_marks` string, 
  `chapter` string, 
  `longitude_latitude` string, 
  `marks` string, 
  `name` string, 
  `place` string, 
  `price` string, 
  `review` string, 
  `score` string, 
  `url` string)
ROW FORMAT SERDE 
  'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe' 
STORED AS INPUTFORMAT 
  'org.apache.hadoop.mapred.TextInputFormat' 
OUTPUTFORMAT 
  'org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat'
  
  

spark 是如何与 hadoop 相关的连接在一起。
spark 是如何与 hdfs 连接在一起的。



关于与 已经安装的 Hive 相连 有个奇怪的事儿：

sqlContext 在 spark-shell 下可以直接查询 Hive 中的数据， 但是用 spark-submit 方式不行。

HiveContext 不管用哪种方式，都可以查询 Hive 中的数据。


举个例子：

```scala
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by T440P on 2016/11/23.
  */
object connect_hive {
  def main(args: Array[String]): Unit = {
      val sc = new SparkContext(new SparkConf().setAppName("connect_hive"))
    sc.setLogLevel("WARN")
    println("Fuck You")
      //val sqlcontext = new SQLContext(sc)
      val hivecontext = new HiveContext(sc)
      val data = hivecontext.tableNames()
      for (name <- data)
        println(name)
    sc.stop()
  }
}
```
上述这段代码，不论是用 spark-submit 的方式执行，还是用 spark-shell 的方式在 10.200.200.57 上执行， 都会打印出 10.200.200.57 上安装的 Hive 的default 数据库中的所有的表的表名。
但是如果把hivecontext 换成  sqlcontext, 那么只有用 spark-shell 的方式执行可以得到 Hive 的 default 数据库中所有表的表名。


能不能得到表名的核心问题是，是否能访问到 hive 的 metastore, 其实你可以在[代码中设定的](http://stackoverflow.com/questions/31980584/how-to-connect-to-a-hive-metastore-programmatically-in-sparksql)，比如
hiveContext.setConf("hive.metastore.uris", "thrift://gs-server-v-127:9083")

（打开 Hive 的配置文件 hive-site.xml ，你会发现 hive-site.xml 中第一个 option 就是 hive.metastore.uris 你可以认为 这个uri 定位了 Hive）


由此如果你想要访问正式集群的 hive, 只需要把  hive.metastore.uris 设为 正式集群 hive 的 uri

卧槽，和我想的不一样，我把 hive.metastore.uris 改成了 准生产集群的 hive, 得到的结果还是 测试集群的结果。
