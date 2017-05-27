package com.wangmeng.spark

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.concurrent.duration.Duration

import scala.concurrent._
import ExecutionContext.Implicits.global


class MyActor extends Actor with ActorLogging{
    override def receive: PartialFunction[Any, Unit] = {
        case _ => println("MyActor");log.info("look, i am writing a log")
    }
}

object Demo extends App {
    
    val system = ActorSystem("MyActors")
    val actorProperties = Props[MyActor]
    
    val actor = system.actorOf(actorProperties)
    
    
    system.scheduler.scheduleOnce(Duration.create(2000, TimeUnit.MILLISECONDS), new Runnable {
        override def run(): Unit = actor ! 42
    })
}