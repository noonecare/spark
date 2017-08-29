package com.wangmeng.scala


import scala.actors._
/**
  * Created by wangmeng on 2017/3/26.
  */
class ActorDemo extends Actor{
    override def act(): Unit = {
        // scala 中的多线程用的是 actor 模型，就是说各个线程都有一个收件箱，各个
        // 线程之间通过收发 msg 来同步
        for(i <- 1 to 10){
            println("msg")
            Thread.sleep(100)
        }
    }
}
