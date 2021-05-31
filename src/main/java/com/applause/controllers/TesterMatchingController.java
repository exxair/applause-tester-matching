package com.applause.controllers;

import com.applause.services.TesterMatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TesterMatchingController {

    private final TesterMatchingService testerMatchingService;

    @GetMapping("/testers")
    public String findTesters(@RequestParam(required = false) List<String> countries, @RequestParam(required = false) List<String> devices, Model model) {
       model.addAttribute("availableDevices", testerMatchingService.getAvailableDevices());
       model.addAttribute("availableCountries", testerMatchingService.getAvailableCountries());
       model.addAttribute("testers", testerMatchingService.findByCountriesAndDevices(countries, devices));
        return "testers";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:testers";
    }
}
