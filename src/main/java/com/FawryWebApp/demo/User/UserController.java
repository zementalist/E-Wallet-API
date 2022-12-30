package com.FawryWebApp.demo.User;

import com.FawryWebApp.demo.AppState.ApplicationState;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FawryWebApp.demo.User.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("check/{email}")
    public boolean userExists(@PathVariable String email) {
        return RegisteredUser.userAlreadyExists(email);
    }


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        boolean is_logged = RegisteredUser.login(email, password);
        if(is_logged)
            return new ResponseEntity<>(RegisteredUser.getInstance(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String password_confirm
    )
    {
        User user = RegisteredUser.register(username, email, password, password_confirm);
        if(user == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.OK);
        else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("checkBalance")
    public String checkBalance() {
        User user = User.getInstance();
        if(user != null)
            return "Current Balance: " + user.getWallet().getBalance() + "$";
        else
            return "Please sign in first.";
    }


}
