package com.example.DigiMath_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAddHelperDTO {

    private Long testId;
    private QuestionDTO question;
    private List<AnswerDTO> answers = new ArrayList<>();


}
