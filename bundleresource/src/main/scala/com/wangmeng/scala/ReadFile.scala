package com.wangmeng.scala

import java.util.Properties

import scala.io.Source

/**
  * Created by wangmeng on 2017/3/26.
  * 演示如何使用相对路径读取文件，如何读取配置文件
  */
object ReadFile extends App{
    // 使用这种方式可以以相对路径的方式打开文件
    val fileUrl = this.getClass.getClassLoader.getResource("names.txt")
    
    val s = Source.fromFile(fileUrl.getFile)
    for(line <- s.getLines())
        {
            println(line)
        }
    
    // 读取配置文件
    val properiesFile = this.getClass.getClassLoader.getResource("cluster.properties")
    val properties = new Properties
    println(properiesFile.getFile)
    properties.load(Source.fromFile(properiesFile.getFile).reader)
    println(properties.get("namenode"))
}
