package com.proqod.engine;

public class HelloClass {
    public String message1 = "Hello World";

    public HelloClass () {
    }

    public HelloClass (String name) {
        this.message1 = "Hello " + name + "!";
    }

    public String getMessage() {
        return message1;
    }
}
