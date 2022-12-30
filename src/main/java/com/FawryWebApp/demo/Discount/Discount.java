package com.FawryWebApp.demo.Discount;
import com.FawryWebApp.demo.Service.*;
import java.util.ArrayList;



public abstract class Discount implements IDiscountBehavior {
	public double percentage;
	
//	public ArrayList<Service> observers;
	
	public Discount(Service service, double percentage) {
		this.percentage = percentage;
	}
	public Discount(double percentage) {
		this.percentage = percentage;
	}
	public double apply(double originalAmount) {
		double discountAmount = (this.percentage / 100.0) * originalAmount;
		return originalAmount - discountAmount;
	}

	public String toString() {
		return this.getClass().getName();
	}
	
//	public void register(Observer observer) {
//		observers.add(observer);
//	}
//
//	public void unregister(Observer observer) {
//		int index = observers.indexOf(observer);
//		observers.remove(index);
//	}
//
//	public void notifyObservers() {
//		for(Observer observer: observers) {
//			observer.update(this);
//		}
//	}
}
