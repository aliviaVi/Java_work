package com.tel_ran;

import java.util.Collection;

public class TransactionService {
    private Account account;
    private Transaction transaction;

    public  long sumOfCancelledTransactions(Collection<Account> accountList){
        return accountList.stream()
                .filter(a->a.getBalance()>0)
                .flatMap(account -> account.getTransactions().stream())
                .filter(trans->trans.getTransactState().equals(State.valueOf("CANCELLED")))
                .reduce(0,(a,b)-> Math.toIntExact(a + b.getSum()),(a, b)->a+b);

    }
}
