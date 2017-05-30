package com.wangmeng.spark.com.wangmeng.spark

import akka.actor.{Actor, ActorSystem, Props, Terminated}
import akka.routing._

object HelloActor {
    case class SayHello(message: String)
}

class HelloActor extends Actor {
    override def receive: PartialFunction[Any, Unit] = {
        case HelloActor.SayHello(message) => Thread.sleep(1000)
            println(message)
    }
}


object RouterDemo extends App {
    val system = ActorSystem("RouterDemo")
    val hello = system.actorOf(Props[HelloActor].withRouter(RoundRobinPool(nrOfInstances = 10)))
    hello ! HelloActor.SayHello("Hello!")
}
