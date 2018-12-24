package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;


    public RegularIncomePaymentDetails getRegularIncomePaymentDetails(String productType, String accountMid) {
        return accountComponent.getRegularIncomePaymentDetails(this.productTypeConverter.convertProduct(productType), accountMid);
    }

    public IncomeLevel getAccountIncomeLevel(String productType) {
        return accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
    }

    public PreservationDetails getAccountPreservationDetails(String productType, String accountMid) {
        return accountComponent.getAccountPreservationDetails(this.productTypeConverter.convertProduct(productType), accountMid);
    }

    public List<FundStrategy> getFundStrategy(String accountMid) {
        return accountComponent.getFundStrategy(accountMid);
    }

    public Combination getCombination(String productType) {
        IncomeLevel il = accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
        RegularIncomePaymentDetails ps= accountComponent.getRegularIncomePaymentDetails(this.productTypeConverter.convertProduct(productType),  il.getAccountToken());
        PreservationDetails pd = accountComponent.getAccountPreservationDetails(this.productTypeConverter.convertProduct(productType), il.getAccountToken());
        List<FundStrategy> fs = accountComponent.getFundStrategy(il.getAccountToken());
        Combination combination = new Combination(il, ps, pd, fs);
        return combination;
    }

    public MappingTest getMapping(String productType) {
        IncomeLevel il = accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
        MappingTest mappingTest = new MappingTest();
        mappingTest.setGrossAnnIncome(Float.valueOf(il.getGrossAnnualIncome()));
        mappingTest.setIncomeStreamPhase("random string");
        mappingTest.setMaxIncomeLevel(Float.valueOf(il.getMaxIncomeLevel()));
        mappingTest.setMinIncomeLevel(Float.valueOf(il.getMinIncomeLevel()));
        return mappingTest;
    }

}
