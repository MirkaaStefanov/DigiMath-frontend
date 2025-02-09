package com.example.DigiMath_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO {

    private Long id;

    private QuestionDTO question;

    private AnswerDTO answer;

    private UserAttemptDTO userAttempt;
}
