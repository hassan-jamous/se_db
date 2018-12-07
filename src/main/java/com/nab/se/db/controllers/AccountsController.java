package com.nab.se.db.controllers;

import com.nab.se.db.components.requestValidators.AccountRequestValidator;
import com.nab.se.db.domains.*;
import com.nab.se.db.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/testData/accounts")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @Autowired
    AccountRequestValidator accountRequestValidator;

    @GetMapping(value = "/paymentStrategy")
    public ResponseEntity<PaymentStrategy> getAccountPaymentStrategy(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPaymentStrategy(accountRequest);
        PaymentStrategy response = accountService.getAccountPaymentStrategy(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/incomeLevel")
    public ResponseEntity<IncomeLevel> getAccountIncomeLevel(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountIncomeLevel(accountRequest);
        IncomeLevel response = accountService.getAccountIncomeLevel(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/preservationDetails")
    public ResponseEntity<PreservationDetails> getAccountPreservationDetails(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        PreservationDetails response = accountService.getAccountPreservationDetails(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/fundStrategy")
    public ResponseEntity<List<FundStrategy>> getAccountPreservationDetails(@PathVariable("accountMid") String accountMid) {
        //this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        List<FundStrategy> response = accountService.getFundStrategy(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


