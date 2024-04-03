package com.simplebank.simplebankapi.model;

public enum TransactionType {
    CREDITO("c"),
    DEBITO("d");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
