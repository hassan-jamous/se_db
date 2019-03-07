package com.nab.se.db.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInformation {
    private String accountMid;
    private String customerNumber;
    private String accountIdDisplay;
}
