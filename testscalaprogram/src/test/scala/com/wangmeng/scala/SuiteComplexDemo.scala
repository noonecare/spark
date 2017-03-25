package com.wangmeng.scala

import junit.framework.{TestCase, TestResult, TestSuite}


/**
  * Created by wangmeng on 2017/3/25.
  * Suite 作用是把多个 testcase 组合成一套， 这一套 testcase 可以批量的一起执行，方便管理
  */

   
object SuiteComplexDemo extends  App{
    class Demo extends TestCase {
        def testDemo() = {
            val a = 1
            assert(a == 2)
        }
}
    
    val demo = new TestSuite(classOf[Demo], classOf[TestComplex])
    val result = new TestResult
    demo run result
    
    
    println(result.errors())
    println(result.wasSuccessful())
    println(result.errorCount())
    println("Number of test cases = " + result.runCount())
    
}
