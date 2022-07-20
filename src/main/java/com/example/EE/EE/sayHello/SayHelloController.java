package com.example.EE.EE.sayHello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    //"say-hello" => "Hello! What are you learning today?"

    //"say-hello"
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello! What are you learning today?";
    }


    //sayHello.jsp
    //"say-hello-jsp" -> sayHello.jsp
    //src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    //src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
    //src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    @RequestMapping("say-hello-jsp")
    public String sayHelloHtml(){
        return "sayHello";
    }
}
