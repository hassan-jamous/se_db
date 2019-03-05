package com.nab.se.db.controllers;

import com.nab.se.db.components.FunctionExample;
import com.nab.se.db.components.requestValidators.AccountRequestValidator;
import com.nab.se.db.domains.*;
import com.nab.se.db.services.AccountService;
import com.nab.se.db.services.InvestorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/testData/accounts")
public class AccountsController {

    @Autowired
    FunctionExample functionExample;

    @Autowired
    private AccountService accountService;

    @Autowired
    private InvestorService investorService;

    @Autowired
    AccountRequestValidator accountRequestValidator;

    @GetMapping(value = "/incomeLevel")
    public ResponseEntity<IncomeLevel> getAccountIncomeLevel(final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountIncomeLevel(accountRequest);
        IncomeLevel response = accountService.getAccountIncomeLevel(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/regularIncomePaymentDetails")
    public ResponseEntity<RegularIncomePaymentDetails> getRegularIncomePaymentDetails(@PathVariable("accountMid") String accountMid,final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetRegularIncomePaymentDetails(accountRequest);
        RegularIncomePaymentDetails response = accountService.getRegularIncomePaymentDetails(accountRequest.getProductType(), accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/preservationDetails")
    public ResponseEntity<PreservationDetails> getAccountPreservationDetails(@PathVariable("accountMid") String accountMid,final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        PreservationDetails response = accountService.getAccountPreservationDetails(accountRequest.getProductType(), accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/fundStrategy")
    public ResponseEntity<List<FundStrategy>> getAccountPreservationDetails(@PathVariable("accountMid") String accountMid) {
        //this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        List<FundStrategy> response = accountService.getFundStrategy(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/combination")
    public ResponseEntity<Combination> getCombination(final AccountRequest accountRequest) throws Exception {

        log.info(accountRequest.toString());
        Combination response = accountService.getCombination(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/fullNameInvestor")
    public ResponseEntity<FullNameInvestor> getFullNameInvestor(final AccountRequest accountRequest) throws Exception {

        log.info(accountRequest.toString());
        FullNameInvestor response = investorService.getFullNameInvestor(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/DateOfBirth")
    public ResponseEntity<String> getDateOfBirth(@PathVariable("partyMid") int partyMid) throws Exception {

        String response = investorService.getDateOfBirth(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/addressInvestor")
    public ResponseEntity<AddressInvestor> getAddressInvestor(@PathVariable("partyMid") String partyMid) throws Exception {

        AddressInvestor response = investorService.getAddressInvestor(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/mapping")
    public ResponseEntity<MappingTest> getMapping(final AccountRequest accountRequest) {
        MappingTest response = accountService.getMapping(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/{accountMid}/accountBalance")
    public ResponseEntity<Double> getAccountPremium(@PathVariable("accountMid") String accountMid) throws Exception{
        return new ResponseEntity<>(this.functionExample.getAccountBalance(accountMid), HttpStatus.OK);
    }

}


