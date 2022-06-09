package com.gojek.banking.repo;

import com.gojek.banking.model.User;

public interface IUserRepo {

    public User findByUserId(long userId);

    public User saveUser(User user);

}
