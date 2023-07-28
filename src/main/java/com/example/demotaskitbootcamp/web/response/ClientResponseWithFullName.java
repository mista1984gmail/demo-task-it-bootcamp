package com.example.demotaskitbootcamp.web.response;

import com.example.demotaskitbootcamp.persistence.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseWithFullName {
    private String fullName;
    private String email;
    private Role role;
}
