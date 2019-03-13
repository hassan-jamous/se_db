package com.nab.se.db.controllers;

import com.nab.se.db.components.FunctionExample;
import com.nab.se.db.components.requestValidators.AccountRequestValidator;
import com.nab.se.db.domains.*;
import com.nab.se.db.services.AccountService;
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
        RegularIncomePaymentDetails response = accountService.getRegularIncomePaymentDetails(accountRequest.getProductType(),accountMid);
        //RegularIncomePaymentDetails response = accountService.getRegularIncomePaymentDetails();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/preservationDetails")
    public ResponseEntity<PreservationDetails> getAccountPreservationDetails(@PathVariable("accountMid") String accountMid,final AccountRequest accountRequest) {
        this.accountRequestValidator.validateGetAccountPreservationDetails(accountRequest);
        PreservationDetails response = accountService.getAccountPreservationDetails(accountRequest.getProductType(), accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/fundStrategy")
    public ResponseEntity<List<FundStrategy>> getFundStrategy(@PathVariable("accountMid") String accountMid) {
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

    @GetMapping(value = "/mapping")
    public ResponseEntity<MappingTest> getMapping(final AccountRequest accountRequest) {
        MappingTest response = accountService.getMapping(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/{accountMid}/accountBalance")
    public ResponseEntity<Double> getAccountPremium(@PathVariable("accountMid") String accountMid) throws Exception{
        return new ResponseEntity<>(this.functionExample.getAccountBalance(accountMid), HttpStatus.OK);
    }
//
    @GetMapping(value = "/{accountMid}/beneficiaryDetails")
    public ResponseEntity<List<BeneficiaryDetails>> getAccountBeneficiaryDetails(@PathVariable("accountMid") String accountMid) {
        List<BeneficiaryDetails> response = accountService.getAccountBeneficiaryDetails(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{productType}/accountMid")

    public ResponseEntity<CustomerInformation> getCustomerInformation(final AccountRequest accountRequest) {
        CustomerInformation response = accountService.getCustomerInformation(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/adviserDetails")
    public ResponseEntity<List<AdviserDetails>> getAccountAdviserDetails(@PathVariable("accountMid") String accountMid) {
        List<AdviserDetails> response = accountService.getAccountAdviserDetails(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/authRepDetails")
    public ResponseEntity<List<AuthRepDetails>> getAuthRepDetails(@PathVariable("accountMid") String accountMid) {
        List<AuthRepDetails> response = accountService.getAuthRepDetails(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/accountParties")
    public ResponseEntity<AccountPartiesDetails> getAccountParties(final AccountRequest accountRequest) throws Exception {

        log.info(accountRequest.toString());
        AccountPartiesDetails response = accountService.getAccountParties(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/futureInvestmentStrategy")
    public ResponseEntity<List<FutureInvestmentStrategy>> getFutureInvestmentStrategy (@PathVariable("accountMid") String accountMid) {
        List<FutureInvestmentStrategy> response = accountService.getFutureInvestmentStrategy(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/drawdownStrategy")
    public ResponseEntity<List<DrawdownStrategy>> getDrawdownStrategy (@PathVariable("accountMid") String accountMid) {
        List<DrawdownStrategy> response = accountService.getDrawdownStrategy(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/accountStrategies")
    public ResponseEntity<AccountStrategiesDetails> getAccountStrategiesDetails(final AccountRequest accountRequest) throws Exception {

        log.info(accountRequest.toString());
        AccountStrategiesDetails response = accountService.getAccountStrategiesDetails(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/BankAccountDetails")
    public ResponseEntity<List<BankAccountDetails>> getBankAccountDetails (@PathVariable("accountMid") String accountMid) {
        List<BankAccountDetails> response = accountService.getBankAccountDetails(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/CurrentFundsInvestment")
    public ResponseEntity<List<CurrentFundInvestment>> getCurrentFundInvestment (@PathVariable("accountMid") String accountMid) {
        List<CurrentFundInvestment> response = accountService.getCurrentFundInvestment(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountMid}/BPAYBillerDetails")
    public ResponseEntity<List<BpayBillerDetails>> getBpayBillerDetails (@PathVariable("accountMid") String accountMid) {
        List<BpayBillerDetails> response = accountService.getBpayBillerDetails(accountMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }










}


