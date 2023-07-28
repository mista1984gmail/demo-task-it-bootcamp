package com.example.demotaskitbootcamp.service.impl;

import com.example.demotaskitbootcamp.persistence.repository.ClientRepository;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


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


}
