package com.wangmeng.scala

import org.scalatest.FunSuite


/**
  * Created by wangmeng on 2017/3/25.
  * 使用 FunSuite 做单元测试
  */
class TestFunSuiteComplex extends FunSuite{
    val testObject = new Complex(1, 2)
    val other = new Complex(3, 4)
    val result = new Complex(4, 6)
    
    // FunSuite 的特色是，不是定义函数，而是用现成的 test 方法定义， test 方法的第一个参数，是字符串描述这个 test 的用
    // 意，第二个参数是 assert 语句
    test("Complex(1, 2) add Complex(2, 4) should be equal to Complex(4, 6)")  {
        assert( (testObject + other) equals result )
    }
}
