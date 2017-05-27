package com.wangmeng.spark

import akka.actor.{Actor, ActorSystem, Props}


class MyActor extends Actor{
    override def receive: PartialFunction[Any, Unit] = {
        case _ => println("MyActor")
    }
}

object Demo extends App {
    
    val system = ActorSystem("MyActors")
    val actorProperties = Props[MyActor]
    
    val actor = system.actorOf(actorProperties)
    actor ! 42
}