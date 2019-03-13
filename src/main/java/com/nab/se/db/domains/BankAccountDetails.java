package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankAccountDetails {
    private String usage;
    private String bsb;
    private String accountNumber;
    private String accountName;
    private String bankName;
    private String branch;
}

