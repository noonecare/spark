package com.wangmeng.scala

/**
  * Created by wangmeng on 2017/3/24.
  */
class Complex(real: Float, img: Float) {
    
    val r: Float = real
    val i: Float = img
    
    // 复数加法
    def +(other: Complex): Complex = {
         new Complex(real + other.r, img + other.i)
    }
    
    override def equals(other: Any): Boolean =
    {
        require(other.isInstanceOf[Complex])
        r == other.asInstanceOf[Complex].r && i == other.asInstanceOf[Complex].i
    }
}
