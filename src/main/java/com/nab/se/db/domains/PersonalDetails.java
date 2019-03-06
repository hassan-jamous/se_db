package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDetails {
    private FullNameInvestor fullNameInvestor;
    private PersonalContactInformation personalContactInformation;
    private BusinessPhoneNumber BusinessContactInformation;
    private AddressInvestor addressInvestor;
    private PostalAddressInvestor postalAddressInvestor;
}