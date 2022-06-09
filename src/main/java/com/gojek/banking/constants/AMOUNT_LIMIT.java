package com.gojek.banking.constants;

public enum AMOUNT_LIMIT {

    MAX_BALANCE((int)1e9);

    private int val;

    AMOUNT_LIMIT(int i) {
        this.val = i;
    }

    public int getVal() {
        return val;
    }
}
