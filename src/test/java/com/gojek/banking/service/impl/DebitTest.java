package com.gojek.banking.service.impl;

import com.gojek.banking.model.User;
import com.gojek.banking.repo.IUserRepo;
import com.gojek.banking.util.PromptUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DebitTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private Debit debit;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void process() {
        User user = new User();
        user.setUserId(1);
        user.setBalance(2.2);
        when(userRepo.findByUserId(1)).thenReturn(user);
        try(MockedStatic<PromptUtil> promptUtil= mockStatic(PromptUtil.class)){
            promptUtil.when(PromptUtil::getInputAmount).thenReturn("1D 1C");
            assertEquals("1D 1C",debit.process());
            verify(userRepo,times(1)).saveUser(userArgumentCaptor.capture());
            user = userArgumentCaptor.getValue();
            assertEquals(1.1,user.getBalance());
        }
    }
}






















