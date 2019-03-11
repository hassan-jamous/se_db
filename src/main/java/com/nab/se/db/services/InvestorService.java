package com.nab.se.db.services;

import com.nab.se.db.components.InvestorComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    @Autowired
    private InvestorComponent investorComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public CustomerToken getCustomerToken(String productType) {

        return investorComponent.getCustomerToken(this.productTypeConverter.convertProduct(productType));
    }

    public Person getPerson(String partyMid) {

        return investorComponent.getPerson((partyMid));
    }


    public List<Phones> getPhones(String partyMid) {

        return investorComponent.getPhones((partyMid));
    }

    public List<Addresses> getAddresses(String partyMid) {

        return investorComponent.getAddresses(partyMid);
    }

    public List<Organisation> getOrganisation(String partyMid) {

        return investorComponent.getOrganisation(partyMid);
    }

    public List<Email> getEmail(String partyMid) {

        return investorComponent.getEmail(partyMid);
    }

    public PersonalDetails getPersonalDetails(String productType) {

        CustomerToken fni = investorComponent.getCustomerToken(this.productTypeConverter.convertProduct(productType));
        Person pci = investorComponent.getPerson(fni.getPartyMid());
        List<Phones> bpn = investorComponent.getPhones(fni.getPartyMid());
        List<Addresses> ai = investorComponent.getAddresses(fni.getPartyMid());
        List<Email> em = investorComponent.getEmail(fni.getPartyMid());
        List<Organisation> org = investorComponent.getOrganisation(fni.getPartyMid());

        PersonalDetails personalDetails = new PersonalDetails(fni, pci, org, bpn, ai, em);
        return personalDetails;
    }

}
