package com.example.demotaskitbootcamp.service.impl;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.persistence.repository.ClientRepository;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.util.FakeClient;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true",
        "spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER"})
public class ClientServiceImplIT {
    public static final Long CLIENT_ID = 1L;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
            .withUsername("username")
            .withPassword("password")
            .withExposedPorts(5432)
            .withReuse(true);

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Test
    void createClient() {
        //given
        Client client = FakeClient.getFirstClient();
        ClientDto clientDtoForSave = FakeClient.getSavedFirstClientDto();

        //when
        clientService.save(clientDtoForSave);

        //then
        Client clientFromDB = clientRepository.findById(CLIENT_ID).get();
        assertEquals(clientFromDB.getId(), CLIENT_ID);
        assertEquals(clientFromDB.getFirstName(), client.getFirstName());
        assertEquals(clientFromDB.getLastName(), client.getLastName());
        assertEquals(clientFromDB.getThirdName(), client.getThirdName());
        assertEquals(clientFromDB.getEmail(), client.getEmail());
        assertEquals(clientFromDB.getRole().name(), client.getRole().name());
    }

    @Test
    void findAllClientsAndSortByEmail(){
        //given
        Client clientFirst = FakeClient.getFirstClient();
        Client clientSecond = FakeClient.getSecondClient();
        Client clientThird = FakeClient.getThirdClient();

        ClientResponseWithFullName clientFirstResponse = FakeClient.getFirstClientResponseWithFullName();
        ClientResponseWithFullName clientSecondResponse = FakeClient.getSecondClientResponseWithFullName();
        ClientResponseWithFullName clientThirdResponse = FakeClient.getThirdClientResponseWithFullName();

        //when
        clientService.save(clientMapper.modelToDto(clientFirst));
        clientService.save(clientMapper.modelToDto(clientSecond));
        clientService.save(clientMapper.modelToDto(clientThird));

        List<ClientResponseWithFullName> sortingClientsFromBD = clientService.findAllAndSortByEmail(0);

        //then
        assertEquals(clientThirdResponse, sortingClientsFromBD.get(0));
        assertEquals(clientFirstResponse, sortingClientsFromBD.get(1));
        assertEquals(clientSecondResponse, sortingClientsFromBD.get(2));
    }

}
