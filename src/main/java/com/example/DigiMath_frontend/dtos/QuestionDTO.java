package com.example.DigiMath_frontend.dtos;

import com.example.DigiMath_frontend.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {


    private Long id;

    private TestDTO test;

    private String text;

    private QuestionType questionType;

    private List<AnswerDTO> answers;

}
