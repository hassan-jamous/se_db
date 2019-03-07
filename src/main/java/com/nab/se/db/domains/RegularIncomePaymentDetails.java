package com.nab.se.db.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegularIncomePaymentDetails {
    private String amount;
    private String frequency;
    private String nextPaymentDate;
    private String payee;
    private String accountMid;
}
