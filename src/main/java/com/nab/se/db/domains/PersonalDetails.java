package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDetails {
    private FullNameInvestor fullNameInvestor;
    private PersonalContactInformation personalContactInformation;
    private BusinessPhoneNumber BusinessContactInformation;
    private Address address;
    private PostalAddress postalAddress;
}