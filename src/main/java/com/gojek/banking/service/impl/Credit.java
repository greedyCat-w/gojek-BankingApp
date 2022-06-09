package com.gojek.banking.service.impl;

import com.gojek.banking.constants.AMOUNT_LIMIT;
import com.gojek.banking.exceptions.MaxBalanceExceeded;
import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.service.BaseOperation;
import com.gojek.banking.util.AmountUtil;
import com.gojek.banking.util.PromptUtil;

import java.math.BigDecimal;

public class Credit implements BaseOperation {

    private IUserRepo userRepo;

    public Credit(IUserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public String process() {

        String inputAmount = PromptUtil.getInputAmount();
        double amount = AmountUtil.getAmountInDouble(inputAmount);
        User user = userRepo.findByUserId(1);
        BigDecimal currBalance = BigDecimal.valueOf(user.getBalance());
        currBalance = currBalance.add(new BigDecimal(amount));
        if(currBalance.doubleValue()> AMOUNT_LIMIT.MAX_BALANCE.getVal()){
            throw new MaxBalanceExceeded(currBalance.doubleValue());
        }
        user.setBalance(currBalance.doubleValue());
        userRepo.saveUser(user);
        return AmountUtil.getAmountInString(user.getBalance());
    }

    @Override
    public String getName() {
        return "Credit";
    }
}
