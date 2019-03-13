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
public class PersonalDetails {
    private CustomerToken customerNumber;
    private Person person;
    private List<Organisation> organisation;
    private List<Phones> phones;
    private List<Addresses> addresses;
    private List<Email> email;
}