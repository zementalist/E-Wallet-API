package com.FawryWebApp.demo.PaymentMethod;
import com.FawryWebApp.demo.Wallet.Wallet;
public abstract class PaymentMethod {
	public boolean execute(Wallet wallet, double amount) {
		if(wallet.getBalance() >= amount) {
			wallet.send(amount);
			return true;
		}
		return false;
		
	}
	public boolean refund(Wallet wallet, double amount) {
		wallet.receive(amount);
		System.out.println("Refunded " + amount + "$");
		return true;
	}
	
	public String toString() {
		return this.getClass().getName();
	}
}
