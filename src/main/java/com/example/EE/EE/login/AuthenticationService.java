package com.example.EE.EE.login;


import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password){
        boolean isValidUserName = username.equalsIgnoreCase("johnjake14");
        boolean isValidPassword = password.equalsIgnoreCase("p@ssw0rd!");

        return isValidPassword && isValidPassword;
    }
}
