package com.example.DigiMath_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {

    private Long id;

    private String title;

    private List<QuestionDTO> questions;
}
