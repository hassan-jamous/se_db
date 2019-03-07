package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRepDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String organisationName;

}
