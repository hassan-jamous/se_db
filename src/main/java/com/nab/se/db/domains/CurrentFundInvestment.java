package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentFundInvestment {
    private Double balance;
    private String asAtDate;
    private String fundName;
    private String fundType;
    private String apirCode;
    private String unitPrice;
    private String unitsHeld;
}

