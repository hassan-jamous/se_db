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
    private String grossAnnIncome;
    private String minIncomeLevel;
    private String maxIncomeLevel;
    private String incomeStreamPhase;
    private String accountToken;
    private String customerNumber;
    private String accountIdDisplay;
}
