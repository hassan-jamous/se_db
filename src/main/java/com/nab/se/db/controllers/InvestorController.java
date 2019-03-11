package com.nab.se.db.controllers;

import com.nab.se.db.domains.*;
import com.nab.se.db.services.InvestorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@Slf4j
@RestController
@RequestMapping(value = "/testData/investor")
public class InvestorController {

    @Autowired
    private InvestorService investorService;


    @GetMapping(value = "/personalDetails")
    public ResponseEntity<PersonalDetails> getPersonalDetails(final AccountRequest accountRequest) throws Exception {

        PersonalDetails response = investorService.getPersonalDetails(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/fullNameInvestor")
    public ResponseEntity<FullNameInvestor> getFullNameInvestor(final AccountRequest accountRequest) throws Exception {

        log.info(accountRequest.toString());
        FullNameInvestor response = investorService.getFullNameInvestor(accountRequest.getProductType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{partyMid}/email")
    public ResponseEntity<List<Email>> getEmail(@PathVariable("partyMid") String partyMid) throws Exception {

        List<Email> response = investorService.getEmail(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/person")
    public ResponseEntity<Person> getPerson(@PathVariable("partyMid") String partyMid) throws Exception {

        Person response = investorService.getPerson(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/businessPhoneNumber")
    public ResponseEntity<BusinessPhoneNumber> getBusinessPhoneNumber(@PathVariable("partyMid") String partyMid) throws Exception {

        BusinessPhoneNumber response = investorService.getBusinessPhoneNumber(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/addresses")
    public ResponseEntity<List<Addresses>> getAddresses(@PathVariable("partyMid") String partyMid) throws Exception {

        List<Addresses> response = investorService.getAddresses(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


