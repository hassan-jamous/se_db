package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public PaymentStrategy getAccountPayemntStrategy(String productType) {
        return accountComponent.getAccountPaymentStrategy(this.productTypeConverter.convertProduct(productType));
    }
}
