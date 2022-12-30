package com.FawryWebApp.demo.Discount;

import com.FawryWebApp.demo.Service.Service;
import com.FawryWebApp.demo.User.User;

public class SpecificDiscount extends Discount{

    public SpecificDiscount(double percentage) {
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
        return "** Discount on specific service **";
    }
}
