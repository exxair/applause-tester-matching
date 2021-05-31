package com.applause.controllers;

import com.applause.services.TesterMatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for searching testers matching given filters.
 */
@Controller
@RequiredArgsConstructor
public class TesterMatchingController {

    private final TesterMatchingService testerMatchingService;

    /**
     * Fills model with testers data based on country and device
     *
     * @param countries list of countries of wanted testers
     * @param devices   list of devices testers found bugs on
     * @param model     holds thymeleaf properties
     * @return testers view name
     */
    @GetMapping("/testers")
    public String findTesters(@RequestParam(required = false) List<String> countries, @RequestParam(required = false) List<String> devices, Model model) {
        model.addAttribute("availableDevices", testerMatchingService.getAvailableDevices());
        model.addAttribute("availableCountries", testerMatchingService.getAvailableCountries());
        model.addAttribute("testers", testerMatchingService.findByCountriesAndDevices(countries, devices));
        return "testers";
    }

    /**
     * Redirects to testers
     *
     * @return testers view name
     */
    @GetMapping("/")
    public String redirect() {
        return "redirect:testers";
    }
}
