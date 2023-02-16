package shop.mtcoding.bankapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.bankapp.handler.ex.CustomException;

@Controller
public class AccountController {

    @GetMapping({ "/", "/account" })
    public String main() {
        // throw new CustomException("인증되지 않았습니다", HttpStatus.UNAUTHORIZED);
        return "account/main";
    }

    @GetMapping("account/detail")
    public String detail() {
        return "account/detail";
    }

    @GetMapping("account/saveForm")
    public String saveForm() {
        return "account/saveForm";
    }

    @GetMapping("account/depositForm")
    public String depositForm() {
        return "account/depositForm";
    }

    @GetMapping("account/transferForm")
    public String transferForm() {
        return "account/transferForm";
    }

    @GetMapping("account/withdrawForm")
    public String withdrawForm() {
        return "account/withdrawForm";
    }
}
