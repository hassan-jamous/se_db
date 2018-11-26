package com.nab.se.db.controllers;

import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/testData/accounts")
    public ResponseEntity<PaymentStrategy> getAccountPaymentStrategy(
            @RequestParam(value = "productType") String productType) {
        PaymentStrategy response = accountService.getAccountPayemntStrategy(productType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
