package com.FawryWebApp.demo.ServiceProvider;

import java.util.ArrayList;
import com.FawryWebApp.demo.ServiceProvider.*;
import com.FawryWebApp.demo.Service.*;
import com.FawryWebApp.demo.Transaction.Transaction;
import com.FawryWebApp.demo.Discount.*;
import com.FawryWebApp.demo.RefundRequest.RefundRequest;
import com.FawryWebApp.demo.PaymentMethod.*;
import com.FawryWebApp.demo.User.User;
import javax.swing.JOptionPane;


public abstract class ServiceProvider {
	public String name;
	public ArrayList<PaymentMethod> availablePayMethods = new ArrayList<PaymentMethod>();
	protected Service service;
	public ServiceProvider(String name) {
		this.name = name;
		this.availablePayMethods.add(new CreditCardPaymentMethod());
	}
	public void addPaymentMethod(PaymentMethod payMethod) {
		this.availablePayMethods.add(payMethod);
	}
	
	public void setService(Service srvc) {
		this.service = srvc;
	}
	

	public String provide(int selectedPayMethodIndex) {
		User user = User.getInstance();
		double costBeforeDiscount = this.service.getCost();
		double costAfterDiscount= this.service.discountAction.apply(costBeforeDiscount);
		PaymentMethod payMethod = this.availablePayMethods.get(selectedPayMethodIndex);
		boolean is_success = payMethod.execute(user.getWallet(), costAfterDiscount);
		String message = "Something Went Wrong!";
		if(is_success) {
			new Transaction(this, payMethod).AddToContext();
			message = "User (" + user.username + ") has charged " + costAfterDiscount + "$ for " + this.service.name + " , from " + this.name;
			if(costBeforeDiscount != costAfterDiscount) {
				int discount_percentage = (int) (100 - (costAfterDiscount / costBeforeDiscount) * 100);
				message += " Discount Applied: " + discount_percentage + "%";
			}
		}
		else {
			message ="Not Enough Balance!";
		}
		System.out.println(message);
		return message;
	}

	public String getServiceName() {
		return this.service.name;
	}
	

	public abstract void displayForm();

}
