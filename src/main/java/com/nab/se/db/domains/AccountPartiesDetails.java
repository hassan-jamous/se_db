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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountPartiesDetails {
    private List<AuthRepDetails> authRepresentatives;
    private List<AdviserDetails> advisers;
    private List<BeneficiaryDetails> beneficiaries;
    private CustomerInformation customerInformation;
}