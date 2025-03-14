package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "digiMath-questions", url = "${backend.base-url}/question")
public interface QuestionClient {
    @PostMapping("/save")
    QuestionDTO save(@RequestBody QuestionDTO questionDTO, @RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    QuestionDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
