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
public class AccountTest {
    
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
    public void BalanceShouldReturnInitial() {
        assertEquals("The initial balance should be 10,000.",
                this.accounts[0].getBalance(),
                10000);
    }
    
    @Test
    public void Withdraw1000ThenCheckBalance() {
        boolean shouldBeTrue = this.accounts[0].withdraw(1000);
        assertEquals("The withdraw function should return true.",
                shouldBeTrue,
                true);
        assertEquals("The account balance should be 9,000.",
                this.accounts[0].getBalance(),
                9000);
    }
    
    @Test
    public void Deposit2000ThenCheckBalance() {
        this.accounts[0].deposit(2000);
        assertEquals("The account balance should be 12,000.",
                this.accounts[0].getBalance(),
                12000);
    }
    
    @Test
    public void Deposit250ThenCheckBalanceString() {
        this.accounts[0].deposit(250);
        assertEquals("The two strings should be equal.",
                this.accounts[0].toString(),
                "Account[0] balance 10250");
    }
    
}
