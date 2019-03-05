package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullNameInvestor {
    private String givenName;
    private String surname;
    private String partyMid;
    private String accountToken;
}
