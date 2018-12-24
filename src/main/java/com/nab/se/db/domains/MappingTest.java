package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MappingTest {
    private float minIncomeLevel;
    private float maxIncomeLevel;
    private float grossAnnIncome;
    private String incomeStreamPhase;
}
