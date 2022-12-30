package com.FawryWebApp.demo.Service;
import com.FawryWebApp.demo.Discount.*;


public abstract class Service {
	public String name;
	private double cost;
	public IDiscountBehavior discountAction;

	public double getCost() {
		return this.cost;
	}
	public Service(String name) {
//		this.provider = provider;
		this.name = name;
//		this.cost = cost;
		this.discountAction = new NoDiscount(0);
	}

	public void setDiscountBehavior(IDiscountBehavior discBehav) {
		if(discBehav != null)
			this.discountAction = discBehav;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
}
