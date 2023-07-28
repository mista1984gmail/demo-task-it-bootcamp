package com.example.demotaskitbootcamp.service.impl;

import com.example.demotaskitbootcamp.config.Constant;
import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.persistence.repository.ClientRepository;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.convertor.ConvertClientService;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ConvertClientService convertClientService;


    @Override
    @Transactional
    public ClientDto save(ClientDto clientDto) {
        log.debug("Save client: {}", clientDto);
        return clientMapper.modelToDto(clientRepository.save(
                clientMapper.dtoToModel(clientDto)));
    }

    @Override
    public List<ClientDto> findAll() {
        log.debug("Find all clients");
        return clientMapper.toListDto(clientRepository.findAll());
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        log.info("Find client by email: {}", email);
        return clientRepository.findByEmail(email);

    }

    public List<ClientDto> findWithPagination(Integer page, Integer clientsPerPage, String sortBy) {
        switch (sortBy){
            case "email":
                return clientMapper.toListDto(clientRepository.findAll(PageRequest.of(page, clientsPerPage, Sort.by("email"))).getContent());
            case "firstName":
                return clientMapper.toListDto(clientRepository.findAll(PageRequest.of(page, clientsPerPage, Sort.by("firstName"))).getContent());
            case "thirdName":
                return clientMapper.toListDto(clientRepository.findAll(PageRequest.of(page, clientsPerPage, Sort.by("thirdName"))).getContent());
            case "lastName":
                return clientMapper.toListDto(clientRepository.findAll(PageRequest.of(page, clientsPerPage, Sort.by("lastName"))).getContent());
            default:
                return clientMapper.toListDto(clientRepository.findAll(PageRequest.of(page, clientsPerPage)).getContent());
        }
    }

    @Override
    public List<ClientResponseWithFullName> findAllAndSortByEmail(Integer page) {
        log.info("Find all clients sorting by email");
        return clientRepository.findAll(PageRequest.of(page, Constant.COUNT_CLIENTS_PER_PAGE, Sort.by("email"))).getContent().stream()
                .map(convertClientService::convertClientToClientResponseWithFullName)
                .collect(Collectors.toList());
    }

}
