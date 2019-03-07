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

        else  if(productType.equals("MKSF")) {
            return 35;
        }

        else  if(productType.equals("MKS")) {
            return 36;
        }

        else if ((productType.equals("MKBS") || productType.equals("MKPS"))) {
            return 11;
        }


        else {
            throw new InvalidProductTypeException();
        }
    }
}
