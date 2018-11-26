package com.nab.se.db.controllers;

import com.nab.se.db.domains.Adviser;
import com.nab.se.db.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping(value = "/testData/advisers/{adviserId}")
    public ResponseEntity<Adviser> getAdviser(@PathVariable int adviserId) {
        Adviser ad= testService.getAdviser(adviserId);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }
}
