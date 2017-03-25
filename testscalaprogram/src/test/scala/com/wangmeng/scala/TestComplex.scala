package com.wangmeng.scala

import junit.framework.TestCase

/**
  * Created by T440P on 2017/3/24.
  */

class TestComplex extends TestCase{
    val testObject = new Complex(1, 2)
    
    def test_+() = {
        val other = new Complex(3, 4)
        val result = new Complex(4, 6)
        assert((testObject + other).equals(result))
    }
    
}
