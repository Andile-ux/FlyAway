package com.andile.Flyaway;


import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
//    @GetMapping("/index")
    public String index(){
        return "templates";
    }
}
