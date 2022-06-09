package com.gojek.banking.service.impl;

import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.service.BaseOperation;
import com.gojek.banking.util.AmountUtil;
import com.gojek.banking.util.PromptUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Debit implements BaseOperation {

    private IUserRepo userRepo;

    public Debit(IUserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public String process() {

        String inputAmount = PromptUtil.getInputAmount();
        double amount = AmountUtil.getAmountInDouble(inputAmount);
        User user = userRepo.findByUserId(1);
        BigDecimal currBalance = BigDecimal.valueOf(user.getBalance());
        currBalance = currBalance.subtract(new BigDecimal(amount));
        user.setBalance(currBalance.round(new MathContext(2, RoundingMode.HALF_UP)).doubleValue());
        userRepo.saveUser(user);
        return AmountUtil.getAmountInString(user.getBalance());
    }

    @Override
    public String getName() {
        return "Debit";
    }

}
