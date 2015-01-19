package com.proqod.engine;

public class HelloClass {
    public String message = "Hello World 2";

    public HelloClass () {
    }

    public HelloClass (String name) {
        this.message = "Hello " + name + "!";
    }

    public String getMessage() {
        return message;
    }
}
