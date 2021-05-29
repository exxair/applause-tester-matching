package com.applause.controllers;

import com.applause.model.dto.TesterMatchingDto;
import com.applause.services.TesterMatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testers")
@RequiredArgsConstructor
public class TesterMatchingController {

    private final TesterMatchingService testerMatchingService;

    @GetMapping("/find")
    public ResponseEntity<List<TesterMatchingDto>> findTesters() {
        return new ResponseEntity<>(testerMatchingService.findAll(), HttpStatus.OK);
    }
}
