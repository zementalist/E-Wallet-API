package com.FawryWebApp.demo.PaymentMethod;
import com.FawryWebApp.demo.Wallet.Wallet;
public class CreditCardPaymentMethod extends PaymentMethod {
	public boolean execute(Wallet wallet, double amount) {
		System.out.println("Payment Method: Credit Card");
		return super.execute(wallet, amount);
	}
	public boolean refund(Wallet wallet, double amount) {
		System.out.println("Refunded " + amount + "$ via Credit Card");
		return super.refund(wallet, amount);
	}
	
	public String toString() {
		return "Credit Card";
	}
}
