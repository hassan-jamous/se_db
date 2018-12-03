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
    private String ComponentType;
    private String RestrictedAmount;
    private String UnrestrictedAmount;
    private String TaxFreeAmt;
    private String ReleaseConditionYn;
    private String FullTaxFreeYn;
    private String TaxFreePercent;
    private String DisplayPreservYn;
    private String EffectiveDate;
    private String AccountMid;

}
