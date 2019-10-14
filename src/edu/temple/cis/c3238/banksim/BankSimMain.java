package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */
public class BankSimMain {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        Semaphore sema = new Semaphore(NACCOUNTS);
        
        Thread[] threads = new Thread[NACCOUNTS];
        Thread testingThread = new TestingThread(bank, sema);
        
        System.out.printf("Bank transfer is in the process.\n");
        testingThread.start();
        for (int i = 0; i < NACCOUNTS; i++) {
            threads[i] = new TransferThread(
                    bank, i, INITIAL_BALANCE, sema, 100);
            threads[i].start();
        }

//      b.test();
    }
}
