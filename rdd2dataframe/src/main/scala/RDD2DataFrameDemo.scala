import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{DataTypes, IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

import scala.annotation.meta.field


object RDD2DataFrameDemo extends App {
    // create spark context
    val conf = new SparkConf().setAppName("RDD to DataFrame").setMaster("local[*]").set("spark.driver.memory", "1g")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    
    // test data to transfer
    val testRDD = sc.parallelize(Array[(Int, String)]((1, "Snow"),(2, "tank"), (3, "the hound"), (4, "Frank")))
    
    // use case class
    case class RowRecord(rank: Int, name: String)
    val dataFrame1 = testRDD.map(row => RowRecord(row._1, row._2)).toDF
    
    // show result
    println("name is :")
    dataFrame1.select("name").collect().foreach[Unit]((x)=>println(x))
    println("rank is : ")
    dataFrame1.select("rank").collect.foreach[Unit]((x)=>println(x))
    
    // use schema structed
    val schema = new StructType(Array[StructField](StructField("rank", IntegerType, nullable = true),
        StructField("name", StringType, nullable = true)))
    val dataFrame2 = sqlContext.createDataFrame(testRDD.map(x => Row(x._1, x._2)), schema)
    dataFrame2.printSchema()
    
    // show result
    println("name is :")
    dataFrame2.select("name").collect().foreach[Unit]((x)=>println(x))
    println("rank is : ")
    dataFrame2.select("rank").collect.foreach[Unit]((x)=>println(x))
    
}