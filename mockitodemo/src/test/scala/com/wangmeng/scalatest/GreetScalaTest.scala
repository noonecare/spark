package com.wangmeng.scalatest

import java.io.{ByteArrayOutputStream, PrintStream}

import org.scalatest.FunSuite

/**
  * Created by T440P on 2017/4/8.
  */

class GreetScalaTest extends FunSuite {
    test ("GreetScala should be print in the stdout") {
        val myOut = new ByteArrayOutputStream()
        Console.setOut(new PrintStream(myOut))
        val g = new GreetScala
        g.greet()
        assert(myOut.toString.equals("GreetScala"))
    }
}