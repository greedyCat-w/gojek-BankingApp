package com.gojek.banking.repo.impl;

import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;

import java.util.Map;

public class InMemoryUserRepoImpl implements IUserRepo {

    Map<Long,User> db;

    public InMemoryUserRepoImpl(Map<Long,User> map){
        this.db = map;
    }

    @Override
    public User findByUserId(long userId) {
        return db.get(userId);
    }

    @Override
    public User saveUser(User user) {
        db.put(user.getUserId(),user);
        return db.get(user.getUserId());
    }
}
