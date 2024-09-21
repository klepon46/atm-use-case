package com.klepon46.atm.model;

import java.util.HashMap;
import java.util.Map;

public class Atm {
    private Map<String, Account> accounts;
    private Account currentAccount;

    public Atm(){
        accounts = new HashMap<String, Account>();
    }

    public void login(String name) {

        if(!accounts.containsKey(name)) {
            accounts.put(name, new Account(name));
        }

        currentAccount = accounts.get(name);
        System.out.println("Hello " + currentAccount.getName());
        printBalanceAndDebt();
    }

    public void deposit(Integer amount) {
        currentAccount.deposit(amount);
        settleDebts();
        printBalanceAndDebt();
    }

    public void withdraw(Integer amount) {
        currentAccount.withdraw(amount);
        printBalanceAndDebt();
    }

    private void printBalanceAndDebt(){
        System.out.println("Your Balance is " + currentAccount.getBalance());

        for(String name : currentAccount.getOwedTo().keySet()) {
            System.out.println(name + " owes you " + currentAccount.getOwedTo().get(name));
        }

        for(String name : currentAccount.getOwedFrom().keySet()) {
            System.out.println("You owe " + name + " " + currentAccount.getOwedFrom().get(name));
        }
    }

    public void logout(){
        System.out.println("Goodbye " + currentAccount.getName());
        currentAccount = null;
    }

    public void transfer(String name, Integer amount) {
        if(!accounts.containsKey(name)) {
            System.out.println("Account not found");
            return;
        }

        Account targetAccount = accounts.get(name);
        int availableBalance = currentAccount.getBalance();

        if(availableBalance < amount) {
            currentAccount.withdraw(availableBalance);
            currentAccount.getOwedTo().put(targetAccount.getName(), Math.abs(availableBalance - amount));

            targetAccount.deposit(availableBalance);
            targetAccount.getOwedFrom().put(currentAccount.getName(), Math.abs(availableBalance - amount));
        }else{
            currentAccount.withdraw(amount);
            targetAccount.deposit(amount);
        }

        printBalanceAndDebt();
    }


    public void settleDebts(){
        for(String name : currentAccount.getOwedTo().keySet()) {
            if(currentAccount.getOwedTo().get(name) > 0) {
                int debts = currentAccount.getOwedTo().get(name);
                int pay = debts - currentAccount.getBalance();

                if(pay <= 0){
                    currentAccount.getOwedTo().remove(name);
                    accounts.get(name).getOwedFrom().remove(currentAccount.getName());
                    currentAccount.withdraw(debts);
                    accounts.get(name).deposit(debts);
                }else{
                    currentAccount.withdraw(currentAccount.getBalance());
                    currentAccount.getOwedTo().put(name, Math.abs(pay));
                    accounts.get(name).getOwedFrom().put(currentAccount.getName(), Math.abs(pay));
                    accounts.get(name).deposit(Math.abs(pay));
                }
            }
        }
    }
}
