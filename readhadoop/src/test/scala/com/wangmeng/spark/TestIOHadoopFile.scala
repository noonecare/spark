package com.wangmeng.spark
import java.io.File

import junit.framework.TestCase
import org.apache.commons.io.FileUtils
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import com.wangmeng.spark.Record
import com.databricks.spark.avro._
import com.databricks.spark.csv._
import com.databricks.spark.xml._
import org.apache.spark.rdd.RDD


/**
  * Created by wangmeng on 2017/4/11.
  */
class TestIOHadoopFile extends TestCase{
    
    val sourceData = Array((new LongWritable(1L), new Text("wangmeng")), (new LongWritable(2L), new Text("zhangyue")),
        (new LongWritable(3L), new Text("lihang")), (new LongWritable(4L), new Text("fuxian")))
    
    val conf = new SparkConf()
    conf.setAppName("read and write Hadoop File").setMaster("local[*]").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .set("spark.driver.allowMultipleContexts", "true")
    val sc = new SparkContext(conf)
    val ioHadoopFile = new IOHadoopFile()
    val directory = new File("C:\\tmp\\test_data")
    val url = "file:///C:\\tmp\\test_data"
    val data: RDD[(LongWritable, Text)] = sc.parallelize[(LongWritable, Text)](sourceData)
    
    
    override def tearDown(): Unit = {
        FileUtils.deleteDirectory(directory)
    }
    
    def testReadHadoopFile () {
        data.saveAsHadoopFile(url, classOf[LongWritable], classOf[Text],
            classOf[TextOutputFormat[LongWritable, Text]])
        
        val result = ioHadoopFile.readHadoopFileuseTextInputFormat(url, sc)
        result.foreach(record => println(record._2.toString))
    }
    
    
    def testReadSequenceFile(): Unit = {
        data.saveAsSequenceFile(url)
        val result = ioHadoopFile.readHadoopSequenceFile(url, sc)
        result.foreach(record => println(record._2.toString))
    }
    
    def testReadParquetFile(): Unit = {
        val sqlContext = new SQLContext(sc)
        import sqlContext.implicits._
        
        val schemedaData = data.map(x=>Record(x._1.get(), x._2.toString)).toDF()
        schemedaData.write.parquet(url)
//        val result = ioHadoopFile.readHadoopParquetFile(url, sc)
//        result.foreach(record => println(record._2.toString))
    }
    
    def testReadAvroFile(): Unit = {
        val sqlContext = new SQLContext(sc)
        import sqlContext.implicits._
        
        val schemedaData = data.map(x=>Record(x._1.get(), x._2.toString)).toDF()
        schemedaData.write.avro(url)
    }
    
    def testReadCSVFile(): Unit = {
        val sqlContext = new SQLContext(sc)
        import sqlContext.implicits._
    
        val schemedaData = data.map(x=>Record(x._1.get(), x._2.toString)).toDF()
        schemedaData.write.csv(url)
    }
    
    def testReadXMLFile(): Unit = {
        val sqlContext = new SQLContext(sc)
        import sqlContext.implicits._
    
        val schemedaData = data.map(x=>Record(x._1.get(), x._2.toString)).toDF()
        schemedaData.write.format("com.databricks.spark.xml")
            .option("rootTag", "records")
            .option("rowTag", "record")
            .save(url)
    }
}
