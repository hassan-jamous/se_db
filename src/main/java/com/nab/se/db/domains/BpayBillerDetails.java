package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BpayBillerDetails {
    private String description;
    private String group_test;
    private String refNumber;

}

