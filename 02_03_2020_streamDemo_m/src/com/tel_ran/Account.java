package com.tel_ran;

import java.util.Collection;
import java.util.List;

public class Account {
    private String num;
    private long balance;
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String num, long balance, List<Transaction> transactions) {
        this.num = num;
        this.balance = balance;
        this.transactions = transactions;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public static long sumOfCancelledTransactions(Collection<Account> accountList){
        return accountList.stream()
                .filter(a->a.getBalance()>0)
                .flatMap(account -> account.getTransactions().stream())
                .filter(trans->trans.getTransactState().equals(State.valueOf("CANCELLED")))
                .reduce(0,(a,b)-> Math.toIntExact(a + b.getSum()),(a, b)->a+b);

    }
}
