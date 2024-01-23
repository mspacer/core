package com.core.reflection.proxy1;

public class AccountManagerImpl implements AccountManager {

    @BankingAnnotation(securityLevel = SecurityLevelType.HIGH)
    public double depositInCash(int accountId, int amount) {
        System.out.println("deposit in total: " + amount );
        return 0;
    }

    @BankingAnnotation(securityLevel = SecurityLevelType.HIGH)
    public boolean withdraw(int accountId, int amount) {
        System.out.println("amount withdrawn: " + + amount);
        return true;
    }

    // run again without comment
    // @BankingAnnotation(securityLevel = SecurityLevelType.LOW)
    public boolean convert(double amount) {
        System.out.println("amount converted: " + amount);
        return true;
    }

    @BankingAnnotation // default value MEDIUM
    public boolean transfer(int accountId, double amount) {
        System.out.println("amount transferred: " + amount);
        return true;
    }
}
