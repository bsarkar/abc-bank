package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    protected List<Transaction> transactions;
	protected double balance ;
    
    ReentrantLock lock = new ReentrantLock();

    public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
    public int getAccountType() {
        return accountType;
    }
    
    public List<Transaction> getTransactions() {
		return transactions;
	}

	public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	lock.lock();
        	try {
        		transactions.add(new Transaction(amount));
        		balance += amount ;
        	}
        	finally {
        		lock.unlock();
        	}
        }
    }

    public void deposit(Date trDate , double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	lock.lock();
        	try {
        		transactions.add(new Transaction(trDate , amount));
        		balance += amount ;
        	}
        	finally {
        		lock.unlock();
        	}
        }
    }
    
    public void withdraw(double amount) throws InsufficientFund {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("amount must be greater than zero");
    	} 
    	if( amount > this.balance) {
    		throw new InsufficientFund("Insufficient Fund") ;
    	}
    	else {
    		lock.lock();
    		try {
    			transactions.add(new Transaction(-amount));
    			balance -= amount;
    		}
    		finally {
    			lock.unlock();
    		}
    	}
    }
	
	public void withdraw(Date trDate ,double amount) throws InsufficientFund {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } 
	    if( amount > this.balance) {
    		throw new InsufficientFund("Insufficient Fund") ;
    	}
	    else {
	    	lock.lock();
	    	try {
	    		transactions.add(new Transaction(trDate , -amount));
	    		balance -= amount;
	    	}
	    	finally {
	    		lock.unlock();
	    	}
	    }
	}

	public double interestEarned() {
		return 0 ;
	}
	
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private synchronized double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }


}
