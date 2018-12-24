package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegularIncomePaymentDetails {
    private String amount;
    private String frequency;
    private String nextPaymentDate;
    private String payee;
    private String accountMid;
}
