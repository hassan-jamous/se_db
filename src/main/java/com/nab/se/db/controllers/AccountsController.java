package com.nab.se.db.controllers;

import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/testData/accounts/{accountMid}/PaymentStrategies/{paymentStrategyType}")
    public ResponseEntity<PaymentStrategy> getAccountPaymentStrategy(@PathVariable int accountMid, @PathVariable String paymentStrategyType) {
        PaymentStrategy response = accountService.getAccountPayemntStrategy(accountMid, paymentStrategyType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
