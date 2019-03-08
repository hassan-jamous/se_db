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


    public FullNameInvestor getFullNameInvestor(String productType) {

        return investorComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
    }

    public PersonalContactInformation getPersonalContactInformation(String partyMid) {

        return investorComponent.getPersonalContactInformation((partyMid));
    }

    public BusinessPhoneNumber getBusinessPhoneNumber(String partyMid) {

        return investorComponent.getBusinessPhoneNumber((partyMid));
    }

    public List<Addresses> getAddresses(String partyMid) {

        return investorComponent.getAddresses(partyMid);
    }

    public PersonalDetails getPersonalDetails(String productType) throws Exception {

        FullNameInvestor fni = investorComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
        PersonalContactInformation pci = investorComponent.getPersonalContactInformation(fni.getPartyMid());
        BusinessPhoneNumber bpn = investorComponent.getBusinessPhoneNumber(fni.getPartyMid());
        List<Addresses> ai = investorComponent.getAddresses(fni.getPartyMid());

        PersonalDetails personalDetails = new PersonalDetails(fni, pci, bpn, ai);
        return personalDetails;
    }

}
