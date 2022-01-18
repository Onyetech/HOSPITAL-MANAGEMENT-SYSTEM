package com.example.sanitasapp.controller;

import com.example.sanitasapp.models.Appointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsitePageController {


    @GetMapping("/")
    public String websiteHome(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "websitepage";
    }

    @GetMapping("/ready")
    public String getAppointment(Model model){
        return "thankyou";
    }


    @GetMapping("/index")
    public String dashboard(Model model){

        return "index";
    }

    @GetMapping("/register")
    public String getRegistered(Model model){
        return "register";
    }


}
