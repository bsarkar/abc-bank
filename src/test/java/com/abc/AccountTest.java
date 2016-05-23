package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidInputChecking(){
		Account checkingAccount = new CheckingAccount() ;
		try {
			checkingAccount.withdraw(-100);
		} catch (InsufficientFund e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidInputSavings(){
		Account savingAccount = new SavingAccount();
		savingAccount.deposit(-200);
	}

	@Rule
    public ExpectedException thrown = ExpectedException.none() ;
	
	@Test
	public void testInsufficientFundExcepChecking(){
		thrown.expect(InsufficientFund.class);
		thrown.expectMessage("Insufficient Fund");
		Account checkingAccount = new CheckingAccount() ;
		checkingAccount.withdraw(100);
	}

	private static final double DELTA = 1e-15;
	@Test
	public void testSumTransaction(){
		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(200);
		checkingAccount.deposit(400);
		checkingAccount.withdraw(100);

		assertEquals(500.0, checkingAccount.sumTransactions(), DELTA) ;
	}

	@Test
	public void accountBalance() {
		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(100.0);

		assertEquals(100.0,checkingAccount.getBalance() , DELTA);
	}

	@Test
	public void checkingAccountInterest() {

		Account checkingAccount = new CheckingAccount();
		checkingAccount.deposit(10000.0);

		assertEquals(10.0,checkingAccount.interestEarned() , DELTA);
	}

	@Test
	public void savingsAccountInterest() {
		Account savingAccount = new SavingAccount();
		savingAccount.deposit(1500.0);

		assertEquals(2.0,savingAccount.interestEarned() , DELTA);
	}

}
