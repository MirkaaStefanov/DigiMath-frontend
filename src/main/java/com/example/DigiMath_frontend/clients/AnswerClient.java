package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.AnswerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "digiMath-answers", url = "${backend.base-url}/answer")
public interface AnswerClient {
    @PostMapping("/save")
    AnswerDTO save(@RequestBody AnswerDTO answerDTO, @RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    AnswerDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
