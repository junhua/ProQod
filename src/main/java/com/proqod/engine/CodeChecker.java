package com.proqod.engine;

public class CodeChecker {
    public String output = "NON E";

    public CodeChecker () {
    }

    public CodeChecker (String name) {
        this.output = "[a dded] " + name + "!";
    }

    public String getResult() {
        return output;
    }
}
