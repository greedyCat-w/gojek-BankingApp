package com.gojek.banking.service.impl;

import com.gojek.banking.service.BaseOperation;

public class Exit implements BaseOperation {

    @Override
    public String process() {
        System.exit(0);
        return null;
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
