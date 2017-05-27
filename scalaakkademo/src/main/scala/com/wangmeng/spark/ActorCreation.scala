package com.wangmeng.spark

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}


class MyActor extends Actor with ActorLogging{
    override def receive: PartialFunction[Any, Unit] = {
        case _ => println("MyActor");log.info("look, i am writing a log")
    }
}

object Demo extends App {
    
    val system = ActorSystem("MyActors")
    val actorProperties = Props[MyActor]
    
    val actor = system.actorOf(actorProperties)
    actor ! 42
}