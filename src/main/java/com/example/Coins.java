package com.example;

public class Coins {
    private static int balance = 0;

    public void addCoins(int amount) {
        balance += amount;
        System.out.println("Coins: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
