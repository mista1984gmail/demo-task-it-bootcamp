package com.example.demotaskitbootcamp.service;


import com.example.demotaskitbootcamp.service.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);
    List<ClientDto> findAll();
}
