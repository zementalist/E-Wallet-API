package com.FawryWebApp.demo.RefundRequest;
import com.FawryWebApp.demo.Transaction.Transaction;
import com.FawryWebApp.demo.AppState.ApplicationState;
import com.FawryWebApp.demo.User.*;
public class RefundRequest {
	public int transaction_id;
	private Transaction transaction;
	public RefundRequest(int transact_id) {
		this.transaction_id = transact_id;
		if(transaction_id < ApplicationState.transactions.size())
			transaction = ApplicationState.transactions.get(this.transaction_id-1);
	}
	public boolean save() {
		if(User.getInstance() != null && this.getTransaction() != null && User.getInstance().email == this.getTransaction().user.email) {
			ApplicationState.refund_requests.add(this);
			return true;
		}
		return false;
	}
	public Transaction getTransaction() {
		return this.transaction;
	}
	
	public boolean approve() {
		if(User.getInstance() != null && User.getInstance().is_admin) {
			User user = this.transaction.user;
			boolean response = this.transaction.payMethod.refund(user.getWallet(), this.transaction.paidAmount);
			if (response) {
				this.transaction.setAsRefunded();
				ApplicationState.refund_requests.remove(this);
			} else
				System.out.println("Unable to refund payment");
			return response;
		}
		System.out.println("Not-allowed");
		return false;
	}
	private boolean remove() {
		ApplicationState.transactions.remove(this.getTransaction());
		return ApplicationState.refund_requests.remove(this);
	}
	public boolean reject() {
		return this.remove();
	}

	public String toString() {
		return "Transaction Id(" + transaction_id + "), User("+transaction.user.username+"), Service("+transaction.provider.getServiceName()+"), Amount("+transaction.paidAmount+")";
	}
}
