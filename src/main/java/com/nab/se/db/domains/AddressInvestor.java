package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInvestor {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String suburb;
    private String postCode;
}
