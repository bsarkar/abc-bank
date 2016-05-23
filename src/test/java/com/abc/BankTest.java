package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new CheckingAccount());
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void customerSummaryMultAcct() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new CheckingAccount());
		john.openAccount(new SavingAccount());
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
	}

	@Test
	public void getFirstCustomer() {
		Bank bank = new Bank();
		assertEquals("Error", bank.getFirstCustomer());
	}

	@Test
	public void checkingAccount() {
		Bank bank = new Bank();
		Account checkingAccount = new CheckingAccount();
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(100.0);

		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() {
		Bank bank = new Bank();
		Account checkingAccount = new SavingAccount();
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(1500.0);

		assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() {
		Bank bank = new Bank();
		Account checkingAccount = new MaxiSavingAccountOrig();
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
		checkingAccount.deposit(3000.0);

		assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_accountOrig() {
		Bank bank = new Bank();
		Account mAccount = new MaxiSavingAccountOrig();
		bank.addCustomer(new Customer("Bill").openAccount(mAccount));
		mAccount.deposit(3000.0);

		assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_accountMod() {
		Bank bank = new Bank();
		Account mAccount = new MaxiSavingAccount();
		bank.addCustomer(new Customer("Bill").openAccount(mAccount));
		mAccount.deposit(3000.0);

		assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_accountModNoWithdwlLast10() {
		Bank bank = new Bank();
		Account mAccount = new MaxiSavingAccount();
		bank.addCustomer(new Customer("Bill").openAccount(mAccount));

		mAccount.deposit(3000.0);
		mAccount.deposit(2000.0);
		mAccount.withdraw(DateProvider.getInstance().addDays(-15), 1000);

		System.out.println("BalanceNoWith:" + mAccount.getBalance() );
		assertEquals(200.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_accountModWithWithdwlLast10() {
		Bank bank = new Bank();
		Account mAccount = new MaxiSavingAccount();
		bank.addCustomer(new Customer("Bill").openAccount(mAccount));
		
		mAccount.deposit(3000.0);
		mAccount.deposit(2000.0);
		mAccount.withdraw(DateProvider.getInstance().addDays(-5), 1000);
		
		System.out.println("BalanceWithWithdrwl:" + mAccount.getBalance() );
		assertEquals(40.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
	
	 @Test
	    public void totalInterestPaid() {
	    	Bank bank = new Bank() ;
	    	for ( int i = 1 ; i < 3 ; i++) {
	    		Account checkingAccount = new CheckingAccount();
	    		Customer bill = new Customer("Bill-"+ i).openAccount(checkingAccount);
	    		bank.addCustomer(bill);

	    		checkingAccount.deposit(100.0);
	    	}

	        assertEquals(0.2, bank.totalInterestPaid(), DOUBLE_DELTA);
	    	
	    }


}
