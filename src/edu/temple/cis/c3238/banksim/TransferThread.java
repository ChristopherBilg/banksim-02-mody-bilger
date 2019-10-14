package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */
class TransferThread extends Thread {

    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;
    private Semaphore sema;
    private int itter;

    public TransferThread(Bank b, int from, int max, Semaphore sema, int itter) {
        this.bank = b;
        this.fromAccount = from;
        this.maxAmount = max;
        this.sema = sema;
        this.itter = itter;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.itter; i++) {
            try {
                this.sema.acquire();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int toAccount = (int) (bank.size() * Math.random());
            int amount = (int) (maxAmount * Math.random());
            bank.transfer(fromAccount, toAccount, amount);

            sema.release();

            // If we don't have the thread wait for some time, then
            // the semaphore 'sema' will never be 'given up' for the
            // testing thread to use.
            // NOTE: Thread.yield() does not work here, I don't know why
            try {
                Thread.sleep(5); // 5 milliseconds
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Close the whole bank when one transferring thread is finished
        // This is needed in a later task
    }
}
