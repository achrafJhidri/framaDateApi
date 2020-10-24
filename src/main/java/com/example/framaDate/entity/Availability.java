package com.example.framadate.entity;

public enum Availability {
    AVAILABLE("A"),
    NON_AVAILABLE("N"),
    MAYBE("M");

    private String code;

    Availability(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
