/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis.c3238.banksim;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author chris
 */
public class BankTest {
    
    private int initialBalance;
    private int numAccounts;
    
    private Bank bank;
    private Account accounts[];
    
    @Before
    public void setUp() {
        this.initialBalance = 10000;
        this.numAccounts = 10;
        
        this.bank = new Bank(this.numAccounts, this.initialBalance);
        this.accounts = this.bank.accounts;
    }
    
    @Test
    public void AllInitialBalancesShouldBe10000() {
        for (int i=0; i<10; i++)
            assertEquals("This account balance should be " + this.initialBalance,
                    this.accounts[i].getBalance(),
                    this.initialBalance);
    }
    
    @Test
    public void Transfer1000FromAccount0ToAccount9() {
        this.bank.transfer(0, 9, 1000);
        assertEquals("Account 0 should have 9000.",
                this.accounts[0].getBalance(),
                9000);
        assertEquals("Account 9 should have 11000.",
                this.accounts[9].getBalance(),
                11000);
    }
    
    @Test
    public void BankSizeShouldBeEqualToNumAccounts() {
        assertEquals("The bank should have " + this.numAccounts + " accounts",
                this.bank.size(),
                this.numAccounts);
    }
    
}
