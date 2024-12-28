package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.AuthenticationRequest;
import com.example.DigiMath_frontend.dtos.AuthenticationResponse;
import com.example.DigiMath_frontend.dtos.RefreshTokenBodyDTO;
import com.example.DigiMath_frontend.dtos.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;

@FeignClient(name = "digiMath-auth", url = "${backend.base-url}/auth")

public interface AuthenticationClient {
    @PostMapping("/register")
    AuthenticationResponse register(@RequestBody RegisterRequest request);

    @PostMapping("/authenticate")
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);

    @PostMapping("/refresh-token")
    AuthenticationResponse refreshToken(@RequestBody RefreshTokenBodyDTO refreshTokenBody) throws IOException;

    @GetMapping("/logout")
    void logout(@RequestHeader("Authorization") String auth);
}
