import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

/**
  * Created by wangmeng on 2017/3/13.
  */
object Demo extends App{
    val conf = new SparkConf()
    conf.setAppName("KafkaExample").setMaster("local[*]")
    
    
    val ssc = new StreamingContext(conf, Seconds(1))
    
    // 接受的消息来至于 testcrawler topics
    val topics = List(("testcrawler", 1)).toMap
    // 接受的消息来自于 localhost:2181 的kafka 服务器
    // 当前 consumer 是属于 crawler group 的 consumer
    val topicLines = KafkaUtils.createStream(ssc, zkQuorum="localhost:2181", "crawler", topics)
    topicLines.print(1)
    
    ssc.start()
    ssc.awaitTermination()
}
