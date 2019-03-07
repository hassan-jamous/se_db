package com.nab.se.db.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Combination {
    private IncomeLevel incomeLevel;
    private RegularIncomePaymentDetails regIncomePayment;
    private PreservationDetails preservationDetails;
    private List<FundStrategy> fundStrategies;
    private Double accountBalance;
}