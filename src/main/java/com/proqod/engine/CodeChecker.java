package com.proqod.engine;

public class CodeChecker {
    public String r = "Hello Sunardi";

    public CodeChecker () {
    }

    public CodeChecker (String name) {
        this.r = "Hello Sunardi " + name + "!";
    }

    public String getResult() {
        return r;
    }
}
