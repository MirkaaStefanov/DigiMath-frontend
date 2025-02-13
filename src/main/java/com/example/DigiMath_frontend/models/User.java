package com.example.DigiMath_frontend.models;

import com.example.DigiMath_frontend.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    @Size(min = 4)
    private String username;
    @Email
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    private Role role;
    private boolean deleted;

}

