package com.example.DigiMath_frontend.dtos;

import com.example.DigiMath_frontend.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull(message = "The name should not be null!")
    private String firstname;
    private String username;
    @NotNull(message = "The name should not be null!")
    private String lastname;
    private String password;
    private String repeatPassword;
    @Email
    private String email;
    private Role role;
    private int currentStreak = 0;
    private int longestStreak = 0;
    private LocalDate dateLastEntered;
}
