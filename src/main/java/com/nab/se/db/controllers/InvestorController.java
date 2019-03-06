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

    @GetMapping(value = "/{partyMid}/personalContactInformation")
    public ResponseEntity<PersonalContactInformation> getPersonalContactInformation(@PathVariable("partyMid") String partyMid) throws Exception {

        PersonalContactInformation response = investorService.getPersonalContactInformation(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/businessPhoneNumber")
    public ResponseEntity<BusinessPhoneNumber> getBusinessPhoneNumber(@PathVariable("partyMid") String partyMid) throws Exception {

        BusinessPhoneNumber response = investorService.getBusinessPhoneNumber(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{partyMid}/address")
    public ResponseEntity<Address> getAddress(@PathVariable("partyMid") String partyMid) throws Exception {

        Address response = investorService.getAddress(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "/{partyMid}/postalAddress")
    public ResponseEntity<PostalAddress> getPostalAddress(@PathVariable("partyMid") String partyMid) throws Exception {

        PostalAddress response = investorService.getPostalAddress(partyMid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


