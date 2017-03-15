import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils


/**
  * Created by wangmeng on 2017/3/13.
  */
object Demo extends App {
    val conf = new SparkConf()
    conf.setAppName("KafkaExample").setMaster("local[*]")
    
    val ssc = new StreamingContext(conf, Seconds(1))
    val sqlContext = new SQLContext(ssc.sparkContext)
    
    case class Record(test1: String, test2: String)
    
    // 程序把 RDD[(String, String)] 类型的消息转成 DataFrame，然后把 DataFrame 写成 parquet 文件
    def parse(raw: RDD[(String, String)]): Unit = {
        // 把 Case Class 组成的 RDD 转成 DataFrame 是需要
        sqlContext.createDataFrame(raw.map(a => Record(a._1, a._2))).write.format("parquet").
            mode(org.apache.spark.sql.SaveMode.Append).saveAsTable("test")
    }
    
    // 接受的消息来至于 testcrawler topics
    val topics = List(("crawler", 1)).toMap
    // 接受的消息来自于 localhost:2181 的kafka 服务器
    // 当前 consumer 是属于 crawler group 的 consumer
    val topicLines = KafkaUtils.createStream(ssc, zkQuorum = "gs-server-1867:2181", "crawler", topics)
    
    topicLines.print()

    
    topicLines.foreachRDD(parse _)
    
    ssc.start()
    ssc.awaitTermination()
}