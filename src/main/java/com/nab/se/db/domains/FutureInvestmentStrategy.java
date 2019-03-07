package com.nab.se.db.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FutureInvestmentStrategy {
    private String fundName;
    private Float percentage;
}
