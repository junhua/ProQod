package com.example.proqod;

public class CheckStyleWrapper {
    public String message = "Hello World";

    public CheckStyleWrapper () {
    }

    public CheckStyleWrapper (String name) {
        this.message = "Hello " + name + "!";
    }

    public String getMessage() {
        return message;
    }
}
