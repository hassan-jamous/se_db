package com.nab.se.db.services;

import com.nab.se.db.components.AccountComponent;
import com.nab.se.db.components.FunctionExample;
import com.nab.se.db.components.ProductTypeConverter;
import com.nab.se.db.domains.*;
import com.nab.se.db.nonFunctional.aspects.annotations.LogMe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

    @Autowired
    private AccountComponent accountComponent;

    @Autowired
    private ProductTypeConverter productTypeConverter;

    @Autowired
    private FunctionExample functionExample;

    public RegularIncomePaymentDetails getRegularIncomePaymentDetails(String productType, String accountMid) {
        return accountComponent.getRegularIncomePaymentDetails(this.productTypeConverter.convertProduct(productType), accountMid);
    }

    public IncomeLevel getAccountIncomeLevel(String productType) {
        return accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
    }

    public PreservationDetails getAccountPreservationDetails(String productType, String accountMid) {
        return accountComponent.getAccountPreservationDetails(this.productTypeConverter.convertProduct(productType), accountMid);
    }

    public List<FundStrategy> getAccountPreservationDetails(String accountMid) {
        return accountComponent.getFundStrategy(accountMid);
    }

    public Double getAccountBalance(String accountMid) throws Exception{
        return functionExample.getAccountBalance(accountMid);
    }

    public List<FundStrategy> getFundStrategy(String accountMid) {
        return accountComponent.getFundStrategy(accountMid);
    }


    public List<BeneficiaryDetails> getAccountBeneficiaryDetails(String accountMid) {
        return accountComponent.getBeneficiaryDetails(accountMid);

    }

    public List<AdviserDetails> getAccountAdviserDetails(String accountMid) {
        return accountComponent.getAccountAdviserDetails(accountMid);

    }

    public List<AuthRepDetails> getAuthRepDetails(String accountMid) {
        return accountComponent.getAuthRepDetails(accountMid);

    }

    public List<FutureInvestmentStrategy> getFutureInvestmentStrategy(String accountMid) {
        return accountComponent.getFutureInvestmentStrategy(accountMid);

    }

    public List<DrawdownStrategy> getDrawdownStrategy(String accountMid) {
        return accountComponent.getDrawdownStrategy(accountMid);

    }



    public CustomerInformation getCustomerInformation (String productType) {
        return accountComponent.getCustomerInformation(this.productTypeConverter.convertProduct(productType));
    }



    @LogMe
    public Combination getCombination(String productType) throws Exception {
        LOGGER.info("Inside Combination");
        IncomeLevel il = accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
        RegularIncomePaymentDetails ps= accountComponent.getRegularIncomePaymentDetails(this.productTypeConverter.convertProduct(productType),  il.getAccountToken());
        PreservationDetails pd = accountComponent.getAccountPreservationDetails(this.productTypeConverter.convertProduct(productType), il.getAccountToken());
        List<FundStrategy> fs = accountComponent.getFundStrategy(il.getAccountToken());
        Double ab = functionExample.getAccountBalance(il.getAccountToken());

        Combination combination = new Combination(il, ps, pd, fs, ab );
        return combination;
    }

    public MappingTest getMapping(String productType) {
        IncomeLevel il = accountComponent.getAccountIncomeLevel(this.productTypeConverter.convertProduct(productType));
        MappingTest mappingTest = new MappingTest();
        mappingTest.setGrossAnnIncome(Float.valueOf(il.getGrossAnnIncome()));
        mappingTest.setIncomeStreamPhase("random string");
        mappingTest.setMaxIncomeLevel(Float.valueOf(il.getMaxIncomeLevel()));
        mappingTest.setMinIncomeLevel(Float.valueOf(il.getMinIncomeLevel()));
        return mappingTest;
    }

    @LogMe
    public AccountPartiesDetails getAccountParties(String productType) throws Exception {
        LOGGER.info("Into Account Parties Information");
        CustomerInformation am = accountComponent.getCustomerInformation(this.productTypeConverter.convertProduct(productType));
        LOGGER.info("Parties Information - Account Mid" + am.getAccountMid());
        List<AuthRepDetails> ar = accountComponent.getAuthRepDetails(am.getAccountMid());
        List<AdviserDetails> ad = accountComponent.getAccountAdviserDetails(am.getAccountMid());
        List<BeneficiaryDetails> bd = accountComponent.getBeneficiaryDetails(am.getAccountMid());


        AccountPartiesDetails accountPartiesDetails = new AccountPartiesDetails(ar,ad,bd,am);
        return accountPartiesDetails;
    }


    @LogMe
    public AccountStrategiesDetails getAccountStrategiesDetails (String productType) throws Exception {
        LOGGER.info("Into Account Strategies Details");
        CustomerInformation am = accountComponent.getCustomerInformation(this.productTypeConverter.convertProduct(productType));
        LOGGER.info("Parties Information - Account Mid" + am.getAccountMid());
        List<FutureInvestmentStrategy> fi = accountComponent.getFutureInvestmentStrategy(am.getAccountMid());
        List<DrawdownStrategy> dd = accountComponent.getDrawdownStrategy(am.getAccountMid());

        AccountStrategiesDetails accountStrategiesDetails = new AccountStrategiesDetails(fi,dd,am);
        return accountStrategiesDetails;
    }

}
