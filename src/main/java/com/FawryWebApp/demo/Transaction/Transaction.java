package com.FawryWebApp.demo.Transaction;

import com.FawryWebApp.demo.ServiceProvider.*;
import com.FawryWebApp.demo.User.User;
import com.FawryWebApp.demo.PaymentMethod.*;
import com.FawryWebApp.demo.AppState.ApplicationState;
import com.FawryWebApp.demo.Wallet.Wallet;

public class Transaction {
	public User user;
	public ServiceProvider provider;
	public PaymentMethod payMethod;
	public double paidAmount;
	public static int transactions_counter = 0;
	public int transaction_id;

	private boolean is_refunded = false;
	public Transaction(ServiceProvider provider, PaymentMethod payMethod) {
		this.user = User.getInstance();
		this.provider = provider;
		this.payMethod = payMethod;
		this.paidAmount = user.getWallet().getRecentTransactAmount();
		this.transaction_id = ++transactions_counter;
	}
	
	public void AddToContext() {
		ApplicationState.transactions.add(this);
	}

	public void setAsRefunded() {
		this.is_refunded = true;
	}

	public String describe() {
		String message = "Id("+this.transaction_id+"), ";
		if(this.provider != null)
			message += "Provider(" + this.provider.name + "), for(" + this.provider.getServiceName() + "), ";
		message += "Payment Method(" + this.payMethod.toString() + "), Amount(" + this.paidAmount + "$)";
		if(this.is_refunded)
			message += " [REFUNDED].";
		return message;
	}
}
