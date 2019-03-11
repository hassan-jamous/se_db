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

public class PersonalDetails {
    private FullNameInvestor fullNameInvestor;
    private Person person;
    private BusinessPhoneNumber BusinessContactInformation;
    private List<Addresses> addresses;
    private List<Email> email;
}