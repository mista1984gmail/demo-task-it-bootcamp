package com.example.demotaskitbootcamp.service.convertor;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;
import org.springframework.stereotype.Service;

@Service
public class ConvertClientService {

    public ClientResponseWithFullName convertClientToClientResponseWithFullName(Client client){
        StringBuilder fullName = new StringBuilder();
        fullName.append(client.getFirstName()).append(" ").append(client.getThirdName()).append(" ").append(client.getLastName());
        return ClientResponseWithFullName.builder()
                .fullName(fullName.toString())
                .email(client.getEmail())
                .role(client.getRole())
                .build();
    }
}
