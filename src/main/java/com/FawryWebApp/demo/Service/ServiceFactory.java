package com.FawryWebApp.demo.Service;

public class ServiceFactory {
	public Service makeService(String type) {
		if(type.equals("Mobile recharge services")) {
			return new MobileRecharge(type);
		}
		else if(type.equals("Internet Payment services")) {
			return new InternetPayment(type);
		}
		else if(type.equals("Landline services")) {
			return new LandlineService(type);
		}
		else if(type.equals("Donations")) {
			return new DonationService(type);
		}

		return null;
	}
}
