package com.smu.investimentos.auth.account.domain.utils;

public class GenerateNumbers {
    public static String generateNumberAccount(Integer value) {
        StringBuilder sb = new StringBuilder();
        int len=0;

        sb.insert(0, value);
        len = sb.length();
        while(len<6){
            sb= new StringBuilder("0" + sb);
            len++;
        }
        return sb.toString();
    }
}
