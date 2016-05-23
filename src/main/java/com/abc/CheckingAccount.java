package com.abc;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super(Account.CHECKING);
	}
	
	@Override
	public double interestEarned() {
		 return balance * 0.001;
	}
}
