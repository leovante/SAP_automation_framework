package com.constants;

public enum SystemTransactions {

    transactionCode("transactionCode"),
    ;

    private String transaction;

    SystemTransactions(String transaction) {
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }

}
