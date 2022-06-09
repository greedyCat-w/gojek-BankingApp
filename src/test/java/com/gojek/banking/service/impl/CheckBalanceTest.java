package com.gojek.banking.service.impl;

import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckBalanceTest {


    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private CheckBalance checkBalance;

    @Test
    void process() {

        User user = new User();
        user.setUserId(1);
        user.setBalance(1.1);
        when(userRepo.findByUserId(1)).thenReturn(user);
        assertEquals("1D 1C",checkBalance.process());
        verify(userRepo,times(1)).findByUserId(anyLong());
    }

}