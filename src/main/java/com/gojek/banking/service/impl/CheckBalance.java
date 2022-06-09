package com.gojek.banking.service.impl;

import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.service.BaseOperation;
import com.gojek.banking.util.AmountUtil;

public class CheckBalance implements BaseOperation {

    private IUserRepo userRepo;

    public CheckBalance(IUserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public String process() {

        User user = userRepo.findByUserId(1);
        return AmountUtil.getAmountInString(user.getBalance());
    }

    @Override
    public String getName() {
        return "CheckBalance";
    }
}
