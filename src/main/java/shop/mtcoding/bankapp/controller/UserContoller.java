package shop.mtcoding.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserContoller {

    @GetMapping("loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("logout")
    public String logout() {
        return "user/loginForm";
    }
}
