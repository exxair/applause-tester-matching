package com.applause.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TesterMatchingControllerSpringTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindTesters() throws Exception {
        mockMvc.perform(get("/testers").param("countries", "US").param("devices", "iPhone 4"))
               .andExpect(status().isOk())
               .andExpect(model().size(3))
               .andExpect(model().attribute("availableDevices", hasSize(10)))
               .andExpect(model().attribute("availableCountries", hasSize(3)))
               .andExpect(model().attribute("testers", hasSize(2)))
               .andExpect(view().name("testers"));
    }

    @Test
    void shouldRedirectToTesters() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(view().name("redirect:testers"));
    }
}