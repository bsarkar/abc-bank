package com.abc;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MaxiSavingAccount extends Account {

	public MaxiSavingAccount() {
		super(Account.MAXI_SAVINGS);
	}

	@Override
	public double interestEarned() {
		// sort it to break out early
		Collections.sort(transactions, new Comparator<Transaction>() {
			public int compare(Transaction t1 , Transaction t2) {
    			return t1.getTransactionDate().compareTo(t2.getTransactionDate()) ;
    		}
		}) ;
		
		
		Date lDate = DateProvider.getInstance().addDays(-10) ;
    	boolean withdraw= false ;
    	for ( Transaction trn : transactions) {
    		if( trn.getTransactionDate().before(lDate))
    			break ;
    		if ( trn.getAmount() < 0  && trn.getTransactionDate().after(lDate)) {
    			withdraw = true ;
    		}
    	}
    	if(withdraw) {
    		return this.balance * 0.01 ;
    	}
    	else {
    		return this.balance * 0.05 ;
    	}
	}
}
