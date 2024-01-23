package com.core.reflection.proxy1;

 interface AccountManager {
    double depositInCash(int accountId, int amount);
    boolean withdraw(int accountId, int amount);
    boolean convert(double amount);
    boolean transfer(int accountId, double amount);
}
