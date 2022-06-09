package com.gojek.banking.util;

import com.gojek.banking.service.BaseOperation;

import java.util.List;
import java.util.Scanner;

public class PromptUtil {

    private static final Scanner sc = new Scanner(System.in);
    private static final String BASE_PROMPT = "$ ";
    private static final String PROMPT = "=> ";


    public static String getInputAmount(){
        System.out.println(BASE_PROMPT+"Enter Amount: ");
        return getInput();
    }

    public static void print(String message){
        System.out.println(BASE_PROMPT+message);
    }

    public static void printOptions(List<BaseOperation >options){
        int count = 1;
        System.out.println(BASE_PROMPT+"Please select an option: ");
        for(BaseOperation option: options){
            System.out.println("\t"+count+". "+option.getName());
            count++;
        }
    }

    public static String getInput(){
        System.out.print(PROMPT);
        return sc.nextLine();
    }

}
