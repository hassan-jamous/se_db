package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DrawdownStrategy {
    private String fundName;
    private Integer sequence;
}
