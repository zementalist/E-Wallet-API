package com.FawryWebApp.demo.Discount;

import com.FawryWebApp.demo.Service.*;
import com.FawryWebApp.demo.User.*;
public class OverallDiscount extends Discount {


	public OverallDiscount(double percentage) {
		super(percentage);
	}

	@Override
	public double apply(double amount) {
		// If this is the first transaction for the user, apply discount
		if(User.getInstance().getWallet().getRecentTransactAmount() == 0) {
			return super.apply(amount);
		}
		// else, return original service.cost
		return amount;
	}

	public String toString() {
		return "** Discount " + this.percentage + "% on the first transaction **";
	}
	
}
