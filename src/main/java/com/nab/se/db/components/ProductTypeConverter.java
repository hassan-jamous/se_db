package com.nab.se.db.components;

import org.springframework.stereotype.Component;

@Component
public class ProductTypeConverter {

    public int convertProduct(String productType) {
        if(productType.equals("pension")) {
            return 21;
        } else {
            throw new RuntimeException("product type doesnt exist");
        }
    }
}
