package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.RootClient;
import com.example.DigiMath_frontend.dtos.RootDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/root")
public class RootController {
    private final RootClient rootClient;

        @GetMapping("/simplifyRoot")
        public String showForm(Model model) {
            model.addAttribute("rootDTO", new RootDTO());
            return "Root/simplify-root";
        }

        @PostMapping("/simplifyRoot")
        public String simplifyRoot(
                @RequestParam Integer degree,
                @RequestParam Integer number,
                Model model
        ) {
            if (degree == null || number == null) {
                model.addAttribute("error", "Degree and number are required.");
                return "Root/simplify-root";
            }

            List<Integer> result = rootClient.simplifyRoot(degree, number);

            RootDTO rootDTO = new RootDTO();
            rootDTO.setNumber(result.get(2));
            rootDTO.setDegree(result.get(1));

            model.addAttribute("coefficient", result.get(0));
            model.addAttribute("rootDTO", rootDTO);

            return "Root/simplify-root";
        }
    }

