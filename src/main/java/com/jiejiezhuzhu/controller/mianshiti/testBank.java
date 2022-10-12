package com.jiejiezhuzhu.controller.mianshiti;

import com.jiejiezhuzhu.controller.mianshiti.Bank;

public class testBank {
    public static void main(String[] args) {
        Bank bank = new Bank(10);
        System.out.println(bank.hello());
        System.out.println(bank.hello());
        System.out.println(bank.hello());
        System.out.println(bank.hello());
        bank.goodbye(4);
        System.out.println(bank.hello());
    }
}
