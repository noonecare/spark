import java.util.Properties
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import scala.io.Source


/**
  * Created by wangmeng on 2017/3/13.
  */
object Demo extends App {
    // DataFrame 要在本地写出数据（比如写出到一个文件），本地必须有设置正确的 HADOOP_HOME 环境变量，或者像下一句代码
    // 一样指定 HADOOP_HOME, 否则会报 spark java.lang.NullPointerException at java.lang.ProcessBuilder.start 的错误
    // System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master")
    case class Record(test: String)
    
    if ( args.length == 3 )
        {
            // 输入参数表示要把数据写入的目录
            val result_dir = args(0)
            // 写出文件的格式，可选 parquet 或者 text
            val result_format = args(1)
            // 写入输出文件的时间间隔，单位是分钟
            val minutes_per_write = args(2).toInt
            
            // 读取配置文件
            val properiesFile = this.getClass.getClassLoader.getResource("config.properties")
            val properties = new Properties
            properties.load(Source.fromFile(properiesFile.getFile).reader)
    
            val conf = new SparkConf()
            conf.setAppName("KafkaExample").setMaster("local[*]")
    
            val ssc = new StreamingContext(conf, Seconds(1))
            val sqlContext = new SQLContext(ssc.sparkContext)
            
            // 程序把 RDD[(String, String)] 类型的消息转成 DataFrame，然后把 DataFrame 写成 parquet 文件
            def parse(raw: RDD[(String, String)]): Unit = {
                // 除了过比较长一段时间再写之外还需要控制分区，这样才能避免产生过多小文件
                sqlContext.createDataFrame(raw.coalesce(1).map(a => Record(a._2))).write.format(result_format).
                    mode(org.apache.spark.sql.SaveMode.Append).save(result_dir)
            }
            
            val topics = List((properties.getProperty("topic"),
                properties.getProperty("topic-thread").toInt)).toMap
    
            // 当前 consumer 是属于 crawler group 的 consumer
            // todo：在链接 kafka 时，有时候会报 kafka java.io.IOException: 远程主机强迫关闭了一个现有的连接，我认为是 kafka 性能不够的原因
            val topicLines = KafkaUtils.createStream(ssc, zkQuorum = properties.getProperty("zkQuorum"), "datascience", topics)
    
            // 为了避免产生小文件，我每隔较长的一段时间写一次文件
            val toWrite = topicLines.window(Seconds(minutes_per_write * 60), Seconds(minutes_per_write * 60))
            toWrite.foreachRDD(parse _)
            ssc.start()
            ssc.awaitTermination()
            
        }
}