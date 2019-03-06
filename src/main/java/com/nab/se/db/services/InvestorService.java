package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public FullNameInvestor getFullNameInvestor(String productType) {

        return accountComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
    }

    public PersonalContactInformation getPersonalContactInformation(String partyMid) {

        return accountComponent.getPersonalContactInformation((partyMid));
    }

    public BusinessPhoneNumber getBusinessPhoneNumber(String partyMid) {

        return accountComponent.getBusinessPhoneNumber((partyMid));
    }

    public AddressInvestor getAddressInvestor(String partyMid) {

        return accountComponent.getAddressInvestor(partyMid);
    }

    public PostalAddressInvestor getPostalAddressInvestor(String partyMid) {

        return accountComponent.getPostalAddressInvestor(partyMid);
    }

    public PersonalDetails getPersonalDetails(String productType) throws Exception {

        FullNameInvestor fni = accountComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
        PersonalContactInformation pci = accountComponent.getPersonalContactInformation(fni.getPartyMid());
        BusinessPhoneNumber bpn = accountComponent.getBusinessPhoneNumber(fni.getPartyMid());
        AddressInvestor ai = accountComponent.getAddressInvestor(fni.getPartyMid());
        PostalAddressInvestor pai = accountComponent.getPostalAddressInvestor(fni.getPartyMid());

        PersonalDetails personalDetails = new PersonalDetails(fni, pci, bpn, ai, pai);
        return personalDetails;
    }

}
