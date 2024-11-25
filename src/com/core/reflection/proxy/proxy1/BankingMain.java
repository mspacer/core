package com.core.reflection.proxy.proxy1;

public class BankingMain {
    public static void main(String[] args) {
        AccountManager manager = SecurityFactory.registerSecurityObject(new AccountManagerImpl());
        manager.depositInCash(10128336, 6);
        manager.withdraw(64092376, 2);
        manager.convert(200);
        manager.transfer(64092376, 300);
    }
}
