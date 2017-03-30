package com.wangmeng.scala

import junit.framework.{TestCase, TestResult, TestSuite}
import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
  * Created by wangmeng on 2017/3/25.
  * Suite 作用是把多个 testcase 组合成一套， 这一套 testcase 可以批量的一起执行，方便管理
  */
class JustADemoTest extends TestCase {
    def testEqual():Unit = {
        val a = 3
        assert(a == 1)
    }
}


@RunWith(classOf[Suite])
@Suite.SuiteClasses(Array(classOf[ComplexTest], classOf[JustADemoTest]))
class AllTest {
}
