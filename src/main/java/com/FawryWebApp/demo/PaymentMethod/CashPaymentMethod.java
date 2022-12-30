package com.FawryWebApp.demo.PaymentMethod;
import com.FawryWebApp.demo.Wallet.Wallet;
public class CashPaymentMethod extends PaymentMethod  {
	public boolean execute(Wallet wallet, double amount) {
		System.out.println("Payment Method: Cash");
		return super.execute(wallet, amount);
	}
	public boolean refund(Wallet wallet, double amount) {
		System.out.println("Refunded " + amount + "$ Cash");
		return super.refund(wallet, amount);
	}
	
	public String toString() {
		return "Cash";
	}
}
