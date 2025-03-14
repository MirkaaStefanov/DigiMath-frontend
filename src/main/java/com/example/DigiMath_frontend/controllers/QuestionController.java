package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.AnswerClient;
import com.example.DigiMath_frontend.clients.QuestionClient;
import com.example.DigiMath_frontend.clients.TestClient;
import com.example.DigiMath_frontend.dtos.AnswerDTO;
import com.example.DigiMath_frontend.dtos.QuestionAddHelperDTO;
import com.example.DigiMath_frontend.dtos.QuestionDTO;
import com.example.DigiMath_frontend.dtos.TestDTO;
import com.example.DigiMath_frontend.enums.QuestionType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/question")
public class QuestionController {

    private final TestClient testClient;
    private final QuestionClient questionClient;
    private final AnswerClient answerClient;
    private static final String SESSION_TOKEN = "sessionToken";

    @GetMapping("/addQuestionA/{testId}")
    public String addQuestionABCD(@PathVariable Long testId, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);
        QuestionAddHelperDTO theDTO = new QuestionAddHelperDTO();

        TestDTO testDTO = testClient.findById(testId, token);
        theDTO.setTestId(testId);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionType(QuestionType.ABCD);
        questionDTO.setTest(testDTO);
        theDTO.setQuestion(questionDTO);

        for (int i = 0; i < 4; i++) {
            theDTO.getAnswers().add(new AnswerDTO());
        }

        model.addAttribute("theDTO", theDTO);
        return "Question/abcdForm";
    }

    @GetMapping("/addQuestionB/{testId}")
    public String addQuestionTrueFalse(@PathVariable Long testId, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);
        QuestionAddHelperDTO theDTO = new QuestionAddHelperDTO();

        TestDTO testDTO = testClient.findById(testId, token);
        theDTO.setTestId(testId);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionType(QuestionType.TRUEFALSE);
        theDTO.setQuestion(questionDTO);

        AnswerDTO answerTrue = new AnswerDTO();
        answerTrue.setText("Вярно");
        AnswerDTO answerFalse = new AnswerDTO();
        answerFalse.setText("Грешно");

        theDTO.getAnswers().add(answerTrue);
        theDTO.getAnswers().add(answerFalse);

        model.addAttribute("theDTO", theDTO);
        return "Question/trueFalseForm";
    }


    @PostMapping("/submit")
    public String submitQuestion(@ModelAttribute QuestionAddHelperDTO theDTO, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);
        TestDTO testDTO = testClient.findById(theDTO.getTestId(), token);

        theDTO.getQuestion().setTest(testDTO);

        QuestionDTO questionDTO = questionClient.save(theDTO.getQuestion(), token);

        for (AnswerDTO answerDTO : theDTO.getAnswers()) {
            answerDTO.setQuestion(questionDTO);
            answerClient.save(answerDTO, token);
        }

        return "redirect:/question/addQuestionA/" + theDTO.getTestId();
    }

}
