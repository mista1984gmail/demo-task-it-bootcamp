package com.example.demotaskitbootcamp.controller;

import com.example.demotaskitbootcamp.persistence.repository.ClientRepository;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.util.FakeClient;
import com.example.demotaskitbootcamp.web.controller.ClientController;
import com.example.demotaskitbootcamp.web.request.ClientRequest;
import com.example.demotaskitbootcamp.web.response.ClientResponse;
import com.example.demotaskitbootcamp.web.validator.EmailValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    private final String RESOURCE_URL = "/api/v1/clients";
    private final Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientMapper clientMapper;

    @MockBean
    private EmailValidator emailValidator;

    @Test
    void findAll() throws Exception {
        //given
        ClientResponse firstClientResponse = FakeClient.getFirstClientResponse();
        ClientResponse secondClientResponse = FakeClient.getSecondClientResponse();


        var clients = List.of(firstClientResponse, secondClientResponse);

        //when
        when(clientMapper.toListResponse(clientService.findAll()))
                .thenReturn(clients);

        //then
        MvcResult mvcResult = mockMvc.perform(get(RESOURCE_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse = objectMapper.findAndRegisterModules().writeValueAsString(clients);
        assertThat(actualJsonResponse).isEqualToNormalizingPunctuationAndWhitespace(expectedJsonResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URL)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

        verify(clientService, times(3)).findAll();
    }
    @Test
    void save() throws Exception {
        ClientDto clientDto = FakeClient.getFirstClientDto();
        ClientResponse clientResponse = FakeClient.getFirstClientResponse();
        ClientRequest clientRequest = FakeClient.getFirstClientRequest();

        when(clientService.save(any(ClientDto.class))).thenReturn(clientDto);
        when(clientMapper.dtoToResponse(clientDto)).thenReturn(clientResponse);
        when(clientMapper.requestToDto(clientRequest)).thenReturn(clientDto);

        mockMvc.perform(MockMvcRequestBuilders.post(RESOURCE_URL)
                        .content(objectMapper.findAndRegisterModules().writeValueAsString(clientResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ivan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ivanov"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.thirdName").value("Ivanovich"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ivan@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("CUSTOMER_USER"));

        verify(clientService, times(1)).save(
                clientMapper.requestToDto(clientRequest));
    }

    @Test
    void saveFail() throws Exception {
        //given
        ClientRequest clientRequest = new ClientRequest();

        //when
        clientService.save(
                clientMapper.requestToDto(clientRequest));

        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(RESOURCE_URL)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().is5xxServerError())
                .andDo(print());

        verify(clientService).save(
                clientMapper.requestToDto(clientRequest));

    }
}
