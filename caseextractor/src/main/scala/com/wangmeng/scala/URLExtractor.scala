package com.wangmeng.scala

/**
  * Created by wangmeng on 2017/3/26.
  * 把 url 中的 parameter 找出来，做这个例子是为了演示如何 case match vararg pattern.
  */
object URLExtractor {
    
    def unapplySeq(url: String): Option[Seq[String]] = {
        require(url.startsWith("http") || url.startsWith("https"))
        val Array(_, params) = url.split("\\?")
        Some(params.split("&"))
    }
    
    def main(args: Array[String]): Unit = {
        val sampleUrl = "http://www.baidu.com/path?name=wangmeng&skuId=123&itemId=246&ae=1323"
        
        sampleUrl match {
            case URLExtractor(a, _*) => println(a)
            case _ => println("not a proper url")
        }
    }
}
