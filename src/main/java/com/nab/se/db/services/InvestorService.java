package com.nab.se.db.services;

import com.nab.se.db.components.InvestorComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorService {

    @Autowired
    private InvestorComponent investorComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public FullNameInvestor getFullNameInvestor(String productType) {

        return investorComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
    }

    public PersonalContactInformation getPersonalContactInformation(String partyMid) {

        return investorComponent.getPersonalContactInformation((partyMid));
    }

    public BusinessPhoneNumber getBusinessPhoneNumber(String partyMid) {

        return investorComponent.getBusinessPhoneNumber((partyMid));
    }

    public Address getAddress(String partyMid) {

        return investorComponent.getAddress(partyMid);
    }

    public PostalAddress getPostalAddress(String partyMid) {

        return investorComponent.getPostalAddress(partyMid);
    }

    public PersonalDetails getPersonalDetails(String productType) throws Exception {

        FullNameInvestor fni = investorComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
        PersonalContactInformation pci = investorComponent.getPersonalContactInformation(fni.getPartyMid());
        BusinessPhoneNumber bpn = investorComponent.getBusinessPhoneNumber(fni.getPartyMid());
        Address ai = investorComponent.getAddress(fni.getPartyMid());
        PostalAddress pai = investorComponent.getPostalAddress(fni.getPartyMid());

        PersonalDetails personalDetails = new PersonalDetails(fni, pci, bpn, ai, pai);
        return personalDetails;
    }

}
