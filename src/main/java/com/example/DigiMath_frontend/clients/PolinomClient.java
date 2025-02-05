package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.PolinomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "digiMath-polinom", url = "${backend.base-url}/polinom")
public interface PolinomClient {

    @PostMapping("/save")
    PolinomDTO savePolinom(@RequestBody PolinomDTO polinomDTO);

    @GetMapping("/{id}")
    PolinomDTO findById(@PathVariable Long id);

    @GetMapping("/ifRoot")
    List<Double> ifRoot(@RequestParam List<Double> polinomCoef, @RequestParam double root);

    @GetMapping("/theRoots")
    List<String> theRoots(@RequestParam List<Double> polinomCoef);


}
