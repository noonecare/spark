package com.wangmeng.scala

/**
  * Created by wangmeng on 2017/3/25.
  * 截取数字和货币单位，比如 300.00$ 截取出 300.00 和 $
  */

object DemoExtractor {
    
    def unapply(a: String): Option[(String, Char)] = {
        val len = a.length()
        Some(a.substring(0, len - 2), a.charAt(len - 1))
    }
    
    def apply(a: String, b: Char): String = {
        a + b
    }
    def main(args: Array[String]): Unit = {
        val sample = "400.00" + '$'
        
        sample match {
            case DemoExtractor(num, currency) => println(currency)
            case _ => println("wrong input")
        }
    }
}
