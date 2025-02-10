package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "digiMath-question", url = "${backend.base-url}/question")
public interface QuestionClient {

    @PostMapping("/create")
    QuestionDTO create(@RequestBody QuestionDTO questionDTO, @RequestParam Long testId, @RequestHeader("Authorization") String auth);


}
