package com.driver;

public class SavingsAccount extends BankAccount {
    private final double rate;
    private final double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        super(name, balance, 0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= maxWithdrawalLimit) {
            super.withdraw(amount);
        } else {
            throw new Exception("Maximum Withdraw Limit Exceeded");
        }
    }

    public double getSimpleInterest(int years) {
        return getBalance() * (1 + rate * years / 100);
    }

    public double getCompoundInterest(int timesPerYear, int years) {
        return getBalance() * Math.pow(1 + rate / (100 * timesPerYear), timesPerYear * years);
    }

    public double getRate() {
        return rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }
}
