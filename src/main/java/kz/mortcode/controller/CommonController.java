package kz.mortcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    @GetMapping("/home")
    public String showHomePage(){
        return "main-page";
    }
}
