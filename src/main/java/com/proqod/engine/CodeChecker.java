package com.proqod.engine;

public class CodeChecker {
    public String result = "Hello World";

    public CodeChecker () {
    }

    public CodeChecker (String code) {
        this.result = "Hello " + code + "!";
    }

    public String getResult() {
        return result;
    }
}
