package com.nab.se.db.components;

import com.nab.se.db.nonFunctional.exceptions.InvalidProductTypeException;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeConverter {

    public int convertProduct(String productType) {
        if(productType.equals("MKPF")) {
            return 38;
        }
        else  if(productType.equals("MKP")) {
            return 39;
        }

        else {
            throw new InvalidProductTypeException();
        }
    }
}
