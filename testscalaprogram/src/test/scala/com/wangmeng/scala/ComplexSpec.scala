package com.wangmeng.scala

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

/**
  * Created by wangmeng on 2017/3/25.
  * 严格来讲，软件应该先有 Specification, 再做编码
  * 用 scala org.specs2.mutable.Specification 来写测试用例（测试用例和Specification 比较像， 打印出来的测试报告和
  * Specification 特别像）
  */

@RunWith(classOf[JUnitRunner])
class ComplexSpec extends Specification{

    //"1 + 2 * i add 3 + 4 * i shuld be equal " " 4 + 6 * i" 会打印在测试的结果中， 这样的测试结果，看起来很像软件的
    // Specification。
    "1 + 2 * i add 3 + 4 * i should be equal " should {
        " 4 + 6 * i" in {
            val targetObj: Complex = new Complex(1, 2)
            val adder: Complex = new Complex(3, 4)
            val result: Complex = new Complex(4, 6)
            (targetObj + adder) shouldEqual result
        }
    }
}
