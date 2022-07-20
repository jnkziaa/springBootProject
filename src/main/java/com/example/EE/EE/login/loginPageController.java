package com.example.EE.EE.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginPageController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AuthenticationService authenticationService;

    public loginPageController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //Get, POST
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(){
        return "loginPage";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcomePage(@RequestParam String name, @RequestParam String password, ModelMap model){
        boolean validAccount = authenticationService.authenticate(name, password);

        if(validAccount){
            model.put("name", name);
            return "welcome";
        }
        else{

            model.put("errorMessage", "Invalid Credentials");
            return "loginPage";
        }

    }
}
