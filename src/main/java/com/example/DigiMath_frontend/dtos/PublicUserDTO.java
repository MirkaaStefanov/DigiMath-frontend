package com.example.DigiMath_frontend.dtos;

import com.example.DigiMath_frontend.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserDTO {
    private Long id;
    private String firstname;

    @JsonProperty("username")
    private String usernameField;
    private String email;
    private Role role;
}
