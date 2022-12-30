package com.FawryWebApp.demo.AppState;
import com.FawryWebApp.demo.User.User;
import com.FawryWebApp.demo.ServiceProvider.*;
import com.FawryWebApp.demo.Service.*;
import com.FawryWebApp.demo.Transaction.Transaction;
import com.FawryWebApp.demo.Discount.*;
import com.FawryWebApp.demo.RefundRequest.RefundRequest;



import java.util.*;




public class ApplicationState {


	public static List<User> registered_users = new ArrayList<User>(
			Arrays.asList(
			
			));
	public static ServiceProviderFactory sp_factory = new ServiceProviderFactory();
	public static List<String> available_services = new ArrayList<String>(
			Arrays.asList(
					"Mobile recharge services",
					"Internet Payment services",
					"Landline services",
					"Donations"
			));
	public static List<ServiceProvider> available_providers = new ArrayList<ServiceProvider>(
			Arrays.asList(
					sp_factory.makeServiceProvider("Vodafone"),
					sp_factory.makeServiceProvider("Orange"),
					sp_factory.makeServiceProvider("We"),
					sp_factory.makeServiceProvider("Etisalat"),
					sp_factory.makeServiceProvider("Landline"),
					sp_factory.makeServiceProvider("Donation")
			));
//	public static List<Service> available_services = new ArrayList<Service>(
//			Arrays.asList(
//					new MobileRecharge("Mobile recharge services"),
//					new InternetPayment("Internet Payment services"),
//					new LandlineService("Landline services"),
//					new DonationService("Donations")
//			));

	public static String[] mobile_service_providers =  {
			"Vodafone",
			"Etisalat",
			"Orange",
			"We"
		};
		
		public static String[] internet_service_providers =  {
				"Vodafone",
				"Etisalat",
				"Orange",
				"We"
			};
		
		public static String[] landline_options =  {
				"Monthly Receipt",
				"Quarter Receipt",
			};
		
		public static String[] donation_receivers =  {
				"Cancer Hospital",
				"School",
				"NGO",
			};
		
	public static String[][] specific_providers = {
			mobile_service_providers,
			internet_service_providers,
			landline_options,
			donation_receivers
			};
//	public static User logged_user = null;
	public static List<Transaction> transactions = new ArrayList<Transaction>(
			Arrays.asList(
			
			));
	public static List<RefundRequest> refund_requests = new ArrayList<RefundRequest>(
			Arrays.asList(
			
			));
	public static List<Discount> available_discounts = new ArrayList<Discount>(
			Arrays.asList(
					new OverallDiscount(20.)
			));
	public static Service selected_service;
	public static ServiceProvider selected_service_provider;
	

	
	
}
