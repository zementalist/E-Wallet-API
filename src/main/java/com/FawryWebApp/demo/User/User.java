package com.FawryWebApp.demo.User;
import com.FawryWebApp.demo.Wallet.Wallet;
import com.FawryWebApp.demo.AppState.ApplicationState;
import java.util.Optional;


public class User {
	public int id;
	public String username;
	public String email;
	public String password;
	private static User user;
	public boolean is_admin = false;
	protected Wallet wallet;


//	public User(String username, String email, String password) {
//		this.username = username;
//		this.email = email;
//		this.password = password;
//	}
//	
	public static boolean login(String email, String password) {
		Optional<User> userResult = ApplicationState.registered_users.stream().filter(c -> email.equals(c.email)).findFirst();
		if(userResult.isPresent()) {
			User user = userResult.get();
			if(password.equals(user.password)) {
				user = setInstance(user);
				return true;
			}
		}
		return false;
	}
	protected User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User save() {
		ApplicationState.registered_users.add(this);
		return this;
	}
	public void setAsAdmin() {
		this.is_admin = true;
	}
	public static User getInstance() {
		return user;
	}
	
	public Wallet getWallet() {
		return this.wallet;
	}
	public Wallet setWallet(Wallet wallet) {
		this.wallet = wallet;
		return this.wallet;
	}
	public static User setInstance(String username, String email, String password) {
		if(user != null) {
			user.username = username;
			user.email = email;
			user.password = password;
		}
		else {
			user = new User(username, email, password);
		}
		return user;
	}
	private static User setInstance(User _user) {
		if(user != null) {
			user.username = _user.username;
			user.email = _user.email;
			user.password = _user.password;
		}
		else {
			user = _user;
		}
		return user;
	}

	
	
}
