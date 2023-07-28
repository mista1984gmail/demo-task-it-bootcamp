package com.example.demotaskitbootcamp.service.impl;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.persistence.repository.ClientRepository;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.convertor.ConvertClientService;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.util.FakeClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ConvertClientService convertClientService;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImpl(clientRepository, clientMapper, convertClientService);
    }

    @Test
    void save() {
        //given
        ClientDto clientDto = FakeClient.getFirstClientDto();
        Client client = FakeClient.getFirstClient();

        when(clientMapper.dtoToModel(clientDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.modelToDto(client)).thenReturn(clientDto);

        //when
        var savedClient = clientService.save(clientDto);

        //then
        Assertions.assertNotNull(savedClient.getId());
        Assertions.assertNotNull(savedClient.getFirstName());
        Assertions.assertNotNull(savedClient.getLastName());
        Assertions.assertNotNull(savedClient.getThirdName());
        Assertions.assertNotNull(savedClient.getEmail());
        Assertions.assertNotNull(savedClient.getRole());

        Assertions.assertEquals(1L, savedClient.getId());
        Assertions.assertEquals("Ivan", savedClient.getFirstName());
        Assertions.assertEquals("Ivanov", savedClient.getLastName());
        Assertions.assertEquals("Ivanovich", savedClient.getThirdName());
        Assertions.assertEquals("ivan@gmail.com", savedClient.getEmail());
        Assertions.assertEquals("CUSTOMER_USER", savedClient.getRole().name());

        verify(clientRepository, times(1)).save(clientMapper.dtoToModel(clientDto));
    }

    @Test
    void findAll() {
        //given
        ClientDto clientFirstDto = FakeClient.getFirstClientDto();
        ClientDto clientSecondDto = FakeClient.getSecondClientDto();
        var clientsDtoFromDb = List.of(clientFirstDto, clientSecondDto);

        Client clientFirst = FakeClient.getFirstClient();
        Client clientSecond = FakeClient.getSecondClient();
        var clientsFromDb = List.of(clientFirst, clientSecond);

        when(clientMapper.toListDto(clientsFromDb)).thenReturn(clientsDtoFromDb);
        doReturn(clientsFromDb).when(clientRepository).findAll();

        //when
        List<ClientDto> clients = clientService.findAll();

        //then
        Assertions.assertEquals(clients,clientsDtoFromDb);
    }
}
