package com.abc;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	@Test //Test customer statement generation
	public void testApp(){

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingAccount();

		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		assertEquals("Statement for Henry\n" +
				"\n" +
				"Checking Account\n" +
				"  deposit $100.00\n" +
				"Total $100.00\n" +
				"\n" +
				"Savings Account\n" +
				"  deposit $4,000.00\n" +
				"  withdrawal $200.00\n" +
				"Total $3,800.00\n" +
				"\n" +
				"Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount(){
		Customer oscar = new Customer("Oscar").openAccount(new SavingAccount());
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount(){
		Customer oscar = new Customer("Oscar")
		.openAccount(new SavingAccount());
		oscar.openAccount(new CheckingAccount());
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidInput(){
		Account checkingAccount = new CheckingAccount();
		checkingAccount.withdraw(-100);
	}
	
	private static final double DELTA = 1e-15;
	@Test
	public void testSumTransaction(){
		Account checkingAccount = new CheckingAccount();
		Customer oscar = new Customer("Oscar").openAccount(checkingAccount) ;
		oscar.openAccount(checkingAccount) ;
		checkingAccount.deposit(200);
		checkingAccount.deposit(400);
		checkingAccount.withdraw(100);
		assertEquals(500.0, checkingAccount.sumTransactions(), DELTA) ;

	}

	//  @SuppressWarnings("deprecation")
	@Test
	public void testTransfer() {
		Account checkingAccount = new CheckingAccount();
		Account savingAccount = new SavingAccount();
		Customer oscar = new Customer("Oscar").openAccount(checkingAccount) ;
		oscar.openAccount(savingAccount) ;
		checkingAccount.deposit(200);
		oscar.transfer(checkingAccount, savingAccount, 100);

		assertEquals(100.0, checkingAccount.sumTransactions(), DELTA) ;
		assertEquals(100.0, savingAccount.sumTransactions(), DELTA) ;

	}
	
	@Rule
	public ExpectedException thrwn =  ExpectedException.none() ;
	
	@Test
	public void testInsufficientFund() {
		thrwn.expect(InsufficientFund.class);
		thrwn.expectMessage("Insufficient Fund");
		Account checkingAccount = new CheckingAccount();
		Account savingAccount = new SavingAccount();
		Customer oscar = new Customer("Oscar").openAccount(checkingAccount) ;
		oscar.openAccount(savingAccount) ;
		checkingAccount.deposit(200);
		oscar.transfer(checkingAccount, savingAccount, 300);
	}

	@Ignore
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar")
		.openAccount(new SavingAccount());
		oscar.openAccount(new CheckingAccount());
		assertEquals(3, oscar.getNumberOfAccounts());
	}
}
