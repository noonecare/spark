package com.wangmeng.spark

import org.apache.avro.generic.GenericRecord
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapred._
import org.apache.hadoop.mapred.lib.NLineInputFormat
import org.apache.parquet.hadoop.ParquetInputFormat
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


case class Record(rank: Long, name: String)


class IOHadoopFile {
    
    // ** Text
    def readHadoopFileuseTextInputFormat(filePath: String, sc: SparkContext): RDD[(LongWritable, Text)] = {
        // TextInputFormat 是自动以文件文件的行数作为 key 的
        sc.hadoopFile[LongWritable, Text, TextInputFormat](filePath)
    }
    
    def readHadoopUseNLineInputFormat(filePath: String, sc: SparkContext): RDD[(LongWritable, Text)] = {
        // NLine 指的是 NLine 为一个 inputSplit , 每条记录的 key 还是行号
        sc.hadoopFile[LongWritable, Text, NLineInputFormat](filePath)
    }
    
    // ** Sequence File
    def readHadoopSequenceFile(filePath: String, sc: SparkContext): RDD[(LongWritable, Text)] = {
        sc.sequenceFile(filePath, 1)
    }
    
    // ** Avro File
    // ** Parquet File
    
}
