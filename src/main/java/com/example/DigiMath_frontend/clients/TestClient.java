package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.TestDTO;
import com.example.DigiMath_frontend.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "digiMath-test", url = "${backend.base-url}/test")
public interface TestClient {

    @GetMapping("/all")
    List<TestDTO> getAllTests(@RequestHeader("Authorization") String auth);

    @PostMapping("/create")
    TestDTO createTest(@Valid @RequestBody TestDTO testDTO, @RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    TestDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
