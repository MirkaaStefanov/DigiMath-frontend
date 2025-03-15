package com.example.DigiMath_frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "digiMath-root", url = "${backend.base-url}/root")
public interface RootClient {
    @GetMapping("/simplifyRoot")
    List<Integer> simplifyRoot(@RequestParam int degree, @RequestParam int number);
}
