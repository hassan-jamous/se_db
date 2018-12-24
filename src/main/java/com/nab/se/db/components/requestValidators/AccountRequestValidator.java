package com.nab.se.db.components.requestValidators;


import com.nab.se.db.domains.AccountRequest;
import com.nab.se.db.nonFunctional.exceptions.RequestValidationException;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestValidator {

    public void validateGetRegularIncomePaymentDetails(AccountRequest accountRequest){
        if (accountRequest.getLimit() == null || accountRequest.getProductType() == null || accountRequest.getLimit() != 1 || accountRequest.getProductType().isEmpty()) {
            throw new RequestValidationException();
        }
    }

    public void validateGetAccountIncomeLevel(AccountRequest accountRequest){
        if (accountRequest.getLimit() == null || accountRequest.getProductType() == null || accountRequest.getLimit() != 1 || accountRequest.getProductType().isEmpty()) {
            throw new RequestValidationException();
        }
    }

    public void validateGetAccountPreservationDetails(AccountRequest accountRequest){
        if (accountRequest.getLimit() == null || accountRequest.getProductType() == null || accountRequest.getLimit() != 1 || accountRequest.getProductType().isEmpty()) {
            throw new RequestValidationException();
        }
    }
}
