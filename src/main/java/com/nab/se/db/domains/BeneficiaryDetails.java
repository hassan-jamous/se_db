package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BeneficiaryDetails {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String relationship;
    private String beneficiaryType;
    private String portion;
}
