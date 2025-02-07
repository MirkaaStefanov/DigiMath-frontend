package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.PascalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "digiMath-pascal", url = "${backend.base-url}/pascal")
public interface PascalClient {
    @PostMapping("/save")
    PascalDTO savePascal(@RequestBody PascalDTO pascalDTO);

    @GetMapping("/result/{id}")
    String resultPascal(@PathVariable Long id);

}
