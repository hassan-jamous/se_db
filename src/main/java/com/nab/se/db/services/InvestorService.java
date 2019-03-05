package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.components.DobConverter;
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

    @Autowired
    private DobConverter dobConverter;


    public FullNameInvestor getFullNameInvestor(String productType) {

        return accountComponent.getFullNameInvestor(this.productTypeConverter.convertProduct(productType));
    }

    public AddressInvestor getAddressInvestor(String partyMid){

        return accountComponent.getAddressInvestor(partyMid);
    }

    public String getDateOfBirth(int partyMid) {

        return dobConverter.getDateOfBirth(partyMid);

    }

}
