package com.nab.se.db.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Addresses {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String postCode;
    private String state;
    private String suburb;
    private String usageType;
}
