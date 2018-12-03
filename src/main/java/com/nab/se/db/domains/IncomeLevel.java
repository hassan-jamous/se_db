package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeLevel {
    private String grossAnnualIncome;
    private String minIncomeLevel;
    private String maxIncomeLevel;
    private String domainValue;
    private String accountMid;
}
