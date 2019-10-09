/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 *
 * @author chris
 */
public class TestingThread extends Thread {
    private Bank bank;
    private Semaphore sema;
    
    public TestingThread(Bank bank, Semaphore sema) {
        this.bank = bank;
        this.sema = sema;
    }
    
    @Override
    public void run() {
        while (this.bank.isBankOpen()) {
            try {
                sema.acquire(this.bank.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (this.bank.shouldTest()) this.bank.test();
            sema.release(this.bank.size());
            
            Thread.yield();
        }
    }
}
