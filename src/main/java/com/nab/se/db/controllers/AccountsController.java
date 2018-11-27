package com.nab.se.db.controllers;

import com.nab.se.db.components.requestValidators.AccountRequestValidator;
import com.nab.se.db.domains.AccountRequest;
import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @Autowired
    AccountRequestValidator accountRequestValidator;

    @GetMapping(value = "/testData/accounts")
    public ResponseEntity<PaymentStrategy> getAccountPaymentStrategy(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPaymentStrategy(accountRequest);
        PaymentStrategy response = accountService.getAccountPaymentStrategy(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
