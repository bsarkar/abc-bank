package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	public static final String DEPOSIT = "DEPOSIT";
	public static final String WITHDRWAL = "WITHDRAWAL";
//  enum TransactionType { DEPOSIT , WITHDRAWAL } ;

	private final double amount;
    private Date transactionDate;
    private final String TransType ;
    
    
    public String getTransType() {
		return TransType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

    public Transaction(double amount) {
    	if( amount < 0) 
    		TransType = WITHDRWAL ;
    	else
    		TransType = DEPOSIT ;
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    public Transaction(Date trDate , double amount) {
    	if( amount < 0) 
    		TransType = WITHDRWAL ;
    	else
    		TransType = DEPOSIT ;
        this.amount = amount;
        this.transactionDate = trDate;
    }
}
