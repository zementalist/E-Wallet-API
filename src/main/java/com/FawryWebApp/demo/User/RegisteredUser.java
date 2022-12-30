package com.FawryWebApp.demo.User;
import com.FawryWebApp.demo.AppState.ApplicationState;
import com.FawryWebApp.demo.Wallet.Wallet;
import java.util.Optional;
import java.util.Random;


public class RegisteredUser extends User {
	private static int users_count = 0;

	private RegisteredUser(String username, String email, String password){
		super(username, email, password);
	}

	public static boolean userAlreadyExists(String email) {
		Optional<User> userExistance = ApplicationState.registered_users.stream().filter(c -> email.equals(c.email)).findFirst();
		return !userExistance.isEmpty();
	}

	public static User register(String username, String email, String password, String password_confirm) {

		if(!username.isEmpty() && !email.isEmpty()
				&& !password.isEmpty() && password_confirm.equals(password)
				&& !userAlreadyExists(email)
				) {
			
			User user = new RegisteredUser(username, email, password);
			user.id = ++users_count;
			int random_balance_amount = new Random().nextInt(100, 10000);
			Wallet wallet = new Wallet("12345689", random_balance_amount);
			user.setWallet(wallet);
			user.save();
			return user;
		}
		return null;
	}
	

}
