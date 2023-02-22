package com.compiler;

public class Token {
    private String name;
    private String value;

    public Token(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getTokenName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
