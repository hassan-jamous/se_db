package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentStrategy {
    private String amount;
    private String frequency;
    private String nextPaymentDate;
    private String payee;
}
