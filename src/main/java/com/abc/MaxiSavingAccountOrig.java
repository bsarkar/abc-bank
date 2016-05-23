package com.abc;

public class MaxiSavingAccountOrig extends Account{

	public MaxiSavingAccountOrig() {
		super(Account.MAXI_SAVINGS);
	}
	
	@Override
	public double interestEarned() {
		if (balance <= 1000)
            return balance * 0.02;
        if (balance <= 2000)
            return 20 + (balance-1000) * 0.05;
        return 70 + (balance-2000) * 0.1;
	}
}
