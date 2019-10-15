package edu.temple.cis.c3238.banksim;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */
public class Bank {

    public static final int NTEST = 10;
    public final Account[] accounts;
    private long ntransacts = 0;
    private final int initialBalance;
    private final int numAccounts;

    private boolean isOpen;

    public Bank(int numAccounts, int initialBalance) {
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        accounts = new Account[numAccounts];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(this, i, initialBalance);
        }
        ntransacts = 0;

        this.isOpen = true;
    }

    public void transfer(int from, int to, int amount) {
        accounts[from].waitForAvailableFunds(amount);
        if (!this.isOpen) {
            return;
        }
        if (accounts[from].withdraw(amount)) {
            accounts[to].deposit(amount);
        }
    }

    public void test() {
        int sum = 0;
        for (Account account : accounts) {
            System.out.printf("%s %s%n",
                    Thread.currentThread().toString(), account.toString());
            sum += account.getBalance();
        }
        System.out.println(Thread.currentThread().toString()
                + " Sum: " + sum);
        if (sum != numAccounts * initialBalance) {
            System.out.println(Thread.currentThread().toString()
                    + " Money was gained or lost");
            System.exit(1);
        } else {
            System.out.println(Thread.currentThread().toString()
                    + " The bank is in balance");
        }
    }

    public int size() {
        return accounts.length;
    }

    public synchronized boolean shouldTest() {
        return ++ntransacts % NTEST == 0;
    }

    public synchronized boolean isBankOpen() {
        return this.isOpen;
    }

    public void closeBank() {
        synchronized (this) {
            this.isOpen = false;
        }

        for (Account acc : this.accounts) {
            synchronized (acc) {
                acc.notifyAll();
            }
        }
    }
}
