package com.gojek.banking.util;

import com.gojek.banking.exceptions.InvalidInputAmountException;

import java.math.BigDecimal;

public class AmountUtil {

    public static double getAmountInDouble(String inputAmount) {

        inputAmount= inputAmount.trim();
        String[] inputs = inputAmount.split(" ");
        if(inputs.length>2) return 0;
        double amount = 0;
        try {
            for (String input : inputs) {
                if (input.isEmpty()) continue;
                char exponent = input.charAt(input.length()-1);
                if (isValidExponent(exponent)) {
                    throw new InvalidInputAmountException(inputAmount);
                }
                long curr = Long.parseLong(input.substring(0, input.length() - 1));
                if (exponent == 'C') {
                    amount += (double) curr / Math.min(100, Math.pow(10, input.length() - 1));
                } else amount += curr;
            }
        }catch (NumberFormatException ex){
            throw new InvalidInputAmountException(inputAmount);
        }
        return amount;
    }

    private static boolean isValidExponent(char exponent) {
        return !Character.isDigit(exponent)&&(exponent=='C'||exponent=='D');
    }

    public static String getAmountInString(double amount) {

        if(amount==0) return "0C";
        boolean negative = amount<0;
        amount = Math.abs(amount);
        BigDecimal amountB = new BigDecimal(String.valueOf(amount));
        int dollars = amountB.intValue();
        amountB = amountB.subtract(new BigDecimal(dollars));
        int cents = (int)(amountB.scale()*10*amountB.doubleValue());
        if(negative){
            dollars *= -1;
            cents *= -1;
        }
        StringBuilder amountInString = new StringBuilder();
        if(dollars!=0){
            amountInString.append(dollars).append("D");
        }
        if(cents!=0){
            if(amountInString.length()!=0) amountInString.append(" ");
            amountInString.append(cents).append("C");
        }
        return amountInString.toString();
    }
}
