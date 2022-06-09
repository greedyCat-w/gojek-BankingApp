package com.gojek.banking.service.impl;

import com.gojek.banking.constants.AMOUNT_LIMIT;
import com.gojek.banking.exceptions.MaxBalanceExceeded;
import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.util.PromptUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private Credit credit;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    void setUp(){
        User user = new User();
        user.setUserId(1);
        when(userRepo.findByUserId(1)).thenReturn(user);
    }

    @Test
    void process() {
        try(MockedStatic<PromptUtil> promptUtil= mockStatic(PromptUtil.class)){
            promptUtil.when(PromptUtil::getInputAmount).thenReturn("1D 1C");
            assertEquals("1D 1C",credit.process());
            verify(userRepo,times(1)).saveUser(userArgumentCaptor.capture());
            User user = userArgumentCaptor.getValue();
            assertEquals(1.1,user.getBalance());
        }
    }

    @Test
    void testForMaxBalance(){

        try(MockedStatic<PromptUtil> promptUtil= mockStatic(PromptUtil.class)){
            int max = AMOUNT_LIMIT.MAX_BALANCE.getVal()+1;
            promptUtil.when(PromptUtil::getInputAmount).thenReturn(max+"D");
            assertThrows(MaxBalanceExceeded.class,()->credit.process());
            verify(userRepo,times(0)).saveUser(any());
        }
    }
}






















