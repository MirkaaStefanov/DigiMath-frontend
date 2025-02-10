package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.QuestionClient;
import com.example.DigiMath_frontend.dtos.AnswerDTO;
import com.example.DigiMath_frontend.dtos.QuestionDTO;
import com.example.DigiMath_frontend.dtos.TestDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/question")
public class QuestionController {

    private final QuestionClient questionClient;
    private static final String SESSION_TOKEN = "sessionToken";

    @GetMapping("/question-form/{testId}")
    public String showQuestionForm(@PathVariable Long testId, Model model) {
        QuestionDTO question = new QuestionDTO();; // Initialize test
        question.setAnswers(new ArrayList<>());

        // Ensure exactly 4 answers are initialized
        // Ensure 4 AnswerDTO objects exist
        for (int i = 0; i < 4; i++) {
            AnswerDTO answer = new AnswerDTO();
            answer.setQuestion(question); // Link back to question
            question.getAnswers().add(answer);
        }

        model.addAttribute("question", question);
        model.addAttribute("testId", testId);
        return "Question/question-form";
    }

    @PostMapping("/question-form")
    public String submitQuestionForm(@RequestParam("testId") Long testId, @ModelAttribute QuestionDTO question, BindingResult result, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);
        if (result.hasErrors()) {
            return "question-form";
        }
        questionClient.create(question,testId,token);
        return "redirect:/test/all-tests";
    }
}
