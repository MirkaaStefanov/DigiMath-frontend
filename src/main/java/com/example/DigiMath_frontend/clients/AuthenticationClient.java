package com.example.DigiMath_frontend.clients;

import com.example.DigiMath_frontend.dtos.AuthenticationRequest;
import com.example.DigiMath_frontend.dtos.AuthenticationResponse;
import com.example.DigiMath_frontend.dtos.RefreshTokenBodyDTO;
import com.example.DigiMath_frontend.dtos.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/forgot-password")
        // Sends link to email so the user can change their password
    ResponseEntity<String> forgotPassword(@RequestParam("email") String email);

    @PostMapping("/password-reset")
    ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword);
}
