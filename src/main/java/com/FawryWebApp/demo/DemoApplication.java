package com.FawryWebApp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import com.FawryWebApp.demo.User.RegisteredUser;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
		RegisteredUser.register("ahmed", "ahmed@gmail.com", "123", "123");
		RegisteredUser.register("salah", "salah@gmail.com", "123", "123").setAsAdmin();
	}



}
