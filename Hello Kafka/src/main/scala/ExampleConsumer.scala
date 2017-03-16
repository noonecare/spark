import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils


/**
  * Created by wangmeng on 2017/3/13.
  */
// todo: 这个 project 的 pom.xml 中配的依赖不是公司集群上额依赖的版本，特别是 spark， scala 的版本需要配置。我需要查看公司的
object Demo extends App {
    // DataFrame 要在本地写出数据（比如写出到一个文件），本地必须有设置正确的 HADOOP_HOME 环境变量，或者像下一句代码
    // 一样指定 HADOOP_HOME, 否则会报 spark java.lang.NullPointerException at java.lang.ProcessBuilder.start 的错误
    System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master")
    val conf = new SparkConf()
    conf.setAppName("KafkaExample").setMaster("local[*]")
    
    val ssc = new StreamingContext(conf, Seconds(1))
    val sqlContext = new SQLContext(ssc.sparkContext)
    
    case class Record(test: String)
    
    // 程序把 RDD[(String, String)] 类型的消息转成 DataFrame，然后把 DataFrame 写成 parquet 文件
    // todo: 需要实际接收一条从 爬虫平台上传回来的消息（是个爬取结果）， 然后在决定如何解析。 我认为接受到的消息的是个
    // todo: (String, String) 类型的数据，其中第二个 String 是爬到的 json串（字符串），但究竟是不是还需要验证
    def parse(raw: RDD[(String, String)]): Unit = {
        // 把 Case Class 组成的 RDD 转成 DataFrame 是需要
        sqlContext.createDataFrame(raw.map(a => Record(a._2))).write.format("text").
            mode(org.apache.spark.sql.SaveMode.Append).save("test.parquet")
    }
    
    
    // 接受的消息来至于 testcrawler topics
    val topics = List(("crawler", 1)).toMap
    // 接受的消息来自于 localhost:2181 的kafka 服务器
    // 当前 consumer 是属于 crawler group 的 consumer
    // todo: 应该加个 windows 函数， 每隔比较长的一段时间才把结果写成 parquet 文件， 避免生成小文件
    // todo: 当 kafka 中已经没有了消息时， 这是貌似我这个程序会产生 .crc 空文件， 如果这样的文件最终被导入到Hive/Impala 表中的话，也会减低 Hive/Impala 的性能，需要避免
    // todo：在链接 kafka 时，有时候会报 kafka java.io.IOException: 远程主机强迫关闭了一个现有的连接，我认为是 kafka 性能不够的原因
    val topicLines = KafkaUtils.createStream(ssc, zkQuorum = "gs-server-1867:2181", "datascience", topics)
    
    topicLines.print()

    
    topicLines.foreachRDD(parse _)
    
    ssc.start()
    ssc.awaitTermination()
}