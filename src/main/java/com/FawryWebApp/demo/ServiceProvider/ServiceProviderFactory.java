package com.FawryWebApp.demo.ServiceProvider;

public class ServiceProviderFactory {
	
	public ServiceProvider makeServiceProvider(String type) {
		if(type.equals("Vodafone")) {
			return new Vodafone(type);
		}
		else if(type.equals("Etisalat")) {
			return new Etisalat(type);
		}
		else if(type.equals("Orange")) {
			return new Orange(type);
		}
		else if(type.equals("We")) {
			return new We(type);
		}
		else if(type.equals("Landline")) {
			return new Landline(type);
		}
		else if(type.equals("Donation")) {
			return new Donation(type);
		}
		else if(type.equals("School")) {
			return new School(type);
		}
		else if(type.equals("Cancer Hospital")) {
			return new CancerHospital(type);
		}
		return null;
		
	}

}
