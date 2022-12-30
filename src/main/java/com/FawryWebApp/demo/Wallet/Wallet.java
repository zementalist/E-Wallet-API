package com.FawryWebApp.demo.Wallet;


public class Wallet {

	private String credit_card_num;
	private double balance = 0;
	private double recent_transaction_amount = 0;
	
	public Wallet(String credit_card_num, double current_balance) {
		this.credit_card_num = credit_card_num;
		this.balance = current_balance;
	}
	
	public String getCreditCardNumber() {
		// Make stars to hide all the number except last 4 digits
		String stars ="*".repeat(this.credit_card_num.length()-4);  
		String safe_to_show = stars + this.credit_card_num.substring(this.credit_card_num.length()-4);
		return safe_to_show;
	}
	public double getBalance() {
		return this.balance;
	}
	
	public double getRecentTransactAmount() {
		return this.recent_transaction_amount;
	}
	
	public void receive(double amount) {
		this.balance += amount;
		this.recent_transaction_amount = amount;
	}
	public void send(double amount) {
		this.balance -= amount;
		this.recent_transaction_amount = amount;
	}
}
