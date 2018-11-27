package com.nab.se.db.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    private Integer limit;
    private String productType;
}
