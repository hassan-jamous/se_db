package com.nab.se.db.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreservationDetails {
    private String componentType;
    private String restrictedNonPreserved;
    private String unrestrictedNonPreserved;
    private String taxFreeAmount;
    private String releaseConditionYn;
    private String fullTaxFreeYn;
    private String taxFreePercent;
    private String displayPreservationYn;
    private String effectiveDate;
    private String accountMid;

}
