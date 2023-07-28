package com.example.demotaskitbootcamp.web.controller.request;

import com.example.demotaskitbootcamp.persistence.entity.Role;
import com.example.demotaskitbootcamp.web.validator.OnlyLatinLetters;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @NotNull
    @Size(max = 20)
    @OnlyLatinLetters
    private String firstName;
    @NotNull
    @Size(max = 40)
    @OnlyLatinLetters
    private String thirdName;
    @NotNull
    @Size(max = 40)
    @OnlyLatinLetters
    private String lastName;
    @NotNull
    @Email
    @Size(max = 50)
    private String email;
    @NotNull
    private Role role;

}
