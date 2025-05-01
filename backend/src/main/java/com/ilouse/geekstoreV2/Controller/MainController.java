package com.ilouse.geekstoreV2.Controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*  alt +1 +2 +3  */

@Controller
public class MainController {
    @RequestMapping("/login")
    public String login(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "login";
    }

    @GetMapping("/home")
    public void home(HttpServletResponse response){
        response.setStatus(500);
    }
}
