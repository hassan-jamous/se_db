package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.IncomeLevel;
import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.domains.PreservationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public PaymentStrategy getAccountPaymentStrategy(String productType) {
        return accountComponent.getAccountPaymentStrategy(this.productTypeConverter.convertProduct(productType));
    }

    public IncomeLevel getAccountIncomeLevel(String productType) {
        return accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
    }

    public PreservationDetails getAccountPreservationDetails(String productType) {
        return accountComponent.getAccountPreservationDetails(this.productTypeConverter.convertProduct(productType));
    }
}
