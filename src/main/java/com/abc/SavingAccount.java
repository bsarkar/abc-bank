package com.abc;

public class SavingAccount extends Account{

	public SavingAccount() {
		super(Account.SAVINGS);
	}
	
	@Override
	public double interestEarned() {
		if (balance <= 1000)
            return balance * 0.001;
        else
            return 1 + (balance-1000) * 0.002;
	}
}
