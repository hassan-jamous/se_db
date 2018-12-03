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
    private String gross_annual_income;
    private String min_income_level;
    private String max_income_level;
    private String domain_value;
    private String account_mid;
}
