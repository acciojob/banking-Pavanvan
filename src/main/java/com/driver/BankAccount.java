package com.driver;

public class BankAccount {
    private final String name;
    private double balance;
    private final double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception {
        if (digits * 9 < sum) {
            throw new Exception("Account Number can not be generated");
        }

        StringBuilder sb = new StringBuilder();
        while (sum > 9) {
            sb.append('9');
            sum -= 9;
        }
        sb.append(sum);
        while (sb.length() < digits) {
            sb.append('0');
        }
        return sb.toString();
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (balance - amount >= minBalance) {
            balance -= amount;
        } else {
            throw new Exception("Insufficient Balance");
        }
    }

    public String getName() {
        return name;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getBalance() {
        return balance;
    }
}
