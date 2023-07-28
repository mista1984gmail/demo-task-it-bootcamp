package com.example.demotaskitbootcamp.service.dto;

import com.example.demotaskitbootcamp.persistence.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long id;

    private String firstName;

    private String thirdName;

    private String lastName;

    private String email;

    private Role role;


}
