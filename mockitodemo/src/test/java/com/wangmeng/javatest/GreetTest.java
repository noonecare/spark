package com.wangmeng.javatest;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class GreetTest extends TestCase {
    public void testGreet(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        Greet g = new Greet();
        g.greet();
        final String standardOut = myOut.toString();
        assert standardOut.equals("string in stdout");
    }
}
