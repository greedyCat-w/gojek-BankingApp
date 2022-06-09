package com.gojek.banking.util;

import com.gojek.banking.exceptions.InvalidInputAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountUtilTest {

    @Test
    void getAmountInDouble(){

        assertEquals(53.9,AmountUtil.getAmountInDouble(" 53D 9C "));
        assertEquals(62,AmountUtil.getAmountInDouble(" 53D 900C "));
        assertEquals(53,AmountUtil.getAmountInDouble("53D  "));
        assertEquals(0.53,AmountUtil.getAmountInDouble("  53C"));
        assertEquals(0,AmountUtil.getAmountInDouble("   "));
        assertThrows(InvalidInputAmountException.class,()->AmountUtil.getAmountInDouble("53"));
        assertDoesNotThrow(()->AmountUtil.getAmountInDouble(" "));
        assertThrows(InvalidInputAmountException.class,()->AmountUtil.getAmountInDouble("#%#D 3#%#C"));
    }

    @Test
    void getAmountInString() {

        assertEquals("53D 9C",AmountUtil.getAmountInString(53.9));
        assertEquals("-53D -9C",AmountUtil.getAmountInString(-53.9));
        assertEquals("-53D",AmountUtil.getAmountInString(-53));
        assertEquals("-9C",AmountUtil.getAmountInString(-0.9));
        assertEquals("53D",AmountUtil.getAmountInString(53));
        assertEquals("9C",AmountUtil.getAmountInString(0.9));
        assertEquals("0C",AmountUtil.getAmountInString(0));

    }
}