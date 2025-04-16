package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.PascalClient;
import com.example.DigiMath_frontend.dtos.PascalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/pascal")
public class PascalController {

    private final PascalClient pascalClient;

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("pascal",new PascalDTO());
        return "Pascal/form";
    }

    @PostMapping("/submit")
    public String savePascal(@ModelAttribute PascalDTO pascalDTO){
        PascalDTO pascal = pascalClient.savePascal(pascalDTO);
        return "redirect:/pascal/result/"+pascal.getId();
    }

    @GetMapping("/result/{id}")
    public String pascalResult(@PathVariable Long id, Model model){
        String result = pascalClient.resultPascal(id);
        model.addAttribute("result",result);
        return "Pascal/result";
    }

}
