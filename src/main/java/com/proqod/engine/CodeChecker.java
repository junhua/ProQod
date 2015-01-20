package com.proqod.engine;

public class CodeChecker {
    public String output = "NONE";

    public CodeChecker () {
    }

    public CodeChecker (String name) {
        this.output = "[added] " + name + "!";
    }

    public String getResult() {
        return output;
    }
}
