package com.example.DigiMath_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolinomDTO {

    private Long id;
    private int degree;
    private List<Double> coefficients;

}
