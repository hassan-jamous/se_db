package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdviserDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String organisationName;
    private String phone;
    private String email;
}
