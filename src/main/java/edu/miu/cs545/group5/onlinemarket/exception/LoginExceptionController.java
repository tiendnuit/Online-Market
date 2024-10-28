package edu.miu.cs545.group5.onlinemarket.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginExceptionController {
    @GetMapping(value = {"/access-denied"})
    public String accessDenied() {
        return "access-denied";
    }

}
