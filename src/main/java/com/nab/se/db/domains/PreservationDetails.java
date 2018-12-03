package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreservationDetails {
    private String componentType;
    private String restrictedAmount;
    private String unrestrictedAmount;
    private String taxFreeAmount;
    private String releaseConditionYn;
    private String fullTaxFreeYn;
    private String taxFreePercent;
    private String displayPreservationYn;
    private String effectiveDate;
    private String accountMid;

}
