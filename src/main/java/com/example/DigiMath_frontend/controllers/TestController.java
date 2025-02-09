package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.TestClient;
import com.example.DigiMath_frontend.dtos.TestDTO;
import com.example.DigiMath_frontend.dtos.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TestController {
    private final TestClient testClient;
    private static final String SESSION_TOKEN = "sessionToken";
    private static final String ERRORTXT = "error";

    @GetMapping("/all-tests")
    public String index(Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);

        List<TestDTO> tests = testClient.getAllTests(token);

        model.addAttribute("tests", tests);
        return "Test/show-all";
    }

    @GetMapping("/create")
    public String createTest(Model model){
        TestDTO test = new TestDTO();
        model.addAttribute("test", test);
        return "Test/create";
    }

    @PostMapping("/submit")
    public ModelAndView submitTest(@ModelAttribute(name = "testDTO") TestDTO testDTO, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(SESSION_TOKEN);
        testClient.createTest(testDTO, token);
        return new ModelAndView("redirect:/test/all-tests");
    }
}
