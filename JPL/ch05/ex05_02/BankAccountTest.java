package ch05.ex05_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
    BankAccount bankAccount = new BankAccount(55555, 1000);



    @Test
    public void testDeposit() {
    	bankAccount.deposit(1000);
        
        assertEquals(2000, bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
    	bankAccount.withdraw(100);
        
        assertEquals(900, bankAccount.getBalance());
    }
    
    @Test
    public void testHistory() {
        for(int i = 1; i < 10; i++){
            bankAccount.deposit(i * 10);
        }
        
        BankAccount.History history = bankAccount.history();
        BankAccount.Action action;
        
        for (int i = 1; i < 10; i++) {
            action = history.next();
            assertEquals("55555 : deposit "+(i *10 ), action.toString());
        }
        assertEquals(history.next(), null);
    }
}

    

