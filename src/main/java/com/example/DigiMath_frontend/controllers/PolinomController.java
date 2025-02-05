package com.example.DigiMath_frontend.controllers;

import com.example.DigiMath_frontend.clients.PolinomClient;
import com.example.DigiMath_frontend.dtos.PolinomDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/polinom")
public class PolinomController {

    private final PolinomClient polinomClient;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("polynomial", new PolinomDTO());
        return "Polinom/polynomial_form";
    }

    @PostMapping("/submit")
    public String submitPolynomial(@ModelAttribute PolinomDTO polynomial) {
        PolinomDTO polinomDTO = polinomClient.savePolinom(polynomial);
        return "redirect:/polinom/"+polinomDTO.getId();
    }
    @GetMapping("/{id}")
    public String polinomMenu(@PathVariable Long id, Model model){
        PolinomDTO polinomDTO = polinomClient.findById(id);
        model.addAttribute("polynomial", polinomDTO);
        return "Polinom/check_root";
    }

    @GetMapping("/check")
    public String checkRoot(@RequestParam Long id, @RequestParam double root, Model model) {
        PolinomDTO polynomial = polinomClient.findById(id);
        List<Double> polinomCoef = polynomial.getCoefficients();

        double result = polinomClient.ifRoot(polinomCoef, root).get(polinomClient.ifRoot(polinomCoef, root).size()-1);

        model.addAttribute("polynomial", polynomial);

        if (result == 0) {
            model.addAttribute("message", "Числото " + root + " е корен на полинома.");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Числото " + root + " не е корен на полинома. Остатък: " + result);
            model.addAttribute("messageType", "error");
        }

        return "Polinom/check_root";
    }

    @GetMapping("/roots")
    public String showRoots(@RequestParam Long id, Model model) {
        PolinomDTO polynomial = polinomClient.findById(id);
        if (polynomial != null) {
            List<String> roots = polinomClient.theRoots(polynomial.getCoefficients());
            model.addAttribute("polynomial", polynomial);
            model.addAttribute("roots", roots);
        } else {
            model.addAttribute("message", "Полиномът не е намерен.");
            model.addAttribute("messageType", "error");
        }
        return "Polinom/roots";
    }


}
