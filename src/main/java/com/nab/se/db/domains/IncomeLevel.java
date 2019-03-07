package com.nab.se.db.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeLevel {
    private String grossAnnIncome;
    private String minIncomeLevel;
    private String maxIncomeLevel;
    private String incomeStreamPhase;
    private String accountToken;
    private String customerNumber;
    private String accountIdDisplay;
}
