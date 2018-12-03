package com.nab.se.db.controllers;

import com.nab.se.db.components.requestValidators.AccountRequestValidator;
import com.nab.se.db.domains.AccountRequest;
import com.nab.se.db.domains.IncomeLevel;
import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.domains.PreservationDetails;
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

    @GetMapping(value = "/testData/accounts/paymentStrategy")
    public ResponseEntity<PaymentStrategy> getAccountPaymentStrategy(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPaymentStrategy(accountRequest);
        PaymentStrategy response = accountService.getAccountPaymentStrategy(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/testData/accounts/incomeLevel")
    public ResponseEntity<IncomeLevel> getAccountIncomeLevel(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountIncomeLevel(accountRequest);
        IncomeLevel response = accountService.getAccountIncomeLevel(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/testData/accounts/preservationDetails")
    public ResponseEntity<PreservationDetails> getAccountPreservationDetails(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        PreservationDetails response = accountService.getAccountPreservationDetails(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}


