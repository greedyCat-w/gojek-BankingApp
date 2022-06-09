package com.gojek.banking;

import com.gojek.banking.constants.AMOUNT_LIMIT;
import com.gojek.banking.exceptions.InvalidInputAmountException;
import com.gojek.banking.exceptions.MaxBalanceExceeded;
import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.repo.impl.InMemoryUserRepoImpl;
import com.gojek.banking.service.BaseOperation;
import com.gojek.banking.service.impl.CheckBalance;
import com.gojek.banking.service.impl.Credit;
import com.gojek.banking.service.impl.Debit;
import com.gojek.banking.service.impl.Exit;
import com.gojek.banking.util.PromptUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankingApplication {

    public static void main(String[] args) {

        List<BaseOperation> options = new ArrayList<>();
        initBank(options);
        while(true){
            PromptUtil.printOptions(options);
            int option = Integer.parseInt(PromptUtil.getInput());
            if(option<1||option>options.size()){
                PromptUtil.print("Invalid option "+option);
                continue;
            }
            try {
                String balance = options.get(option-1).process();
                PromptUtil.print(" Balance - "+balance);
            } catch (InvalidInputAmountException ex){
                PromptUtil.print(" Invalid amount entered "+ex.getMessage());
            } catch (MaxBalanceExceeded ex){
                PromptUtil.print(" Maximum allowed balance is "+ AMOUNT_LIMIT.MAX_BALANCE.getVal());
            }
        }
    }

    private static void initBank(List<BaseOperation> options) {
        IUserRepo userRepo = new InMemoryUserRepoImpl(new HashMap<>());
        User user = new User();
        user.setUserId(1L);
        userRepo.saveUser(user);
        options.add(new Credit(userRepo));
        options.add(new Debit(userRepo));
        options.add(new CheckBalance(userRepo));
        options.add(new Exit());
    }

}
