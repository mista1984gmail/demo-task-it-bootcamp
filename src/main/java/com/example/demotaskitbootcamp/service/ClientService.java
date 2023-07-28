package com.example.demotaskitbootcamp.service;


import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDto save(ClientDto clientDto);
    List<ClientDto> findAll();

    Optional<Client> findByEmail(String email);

    List<ClientDto> findWithPagination(Integer page, Integer clientsPerPage, String sortBy);

    List<ClientResponseWithFullName> findAllAndSortByEmail(Integer page);
}
