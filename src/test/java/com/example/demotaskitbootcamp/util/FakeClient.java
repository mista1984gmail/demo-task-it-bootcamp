package com.example.demotaskitbootcamp.util;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.persistence.entity.Role;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.web.request.ClientRequest;
import com.example.demotaskitbootcamp.web.response.ClientResponse;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;

import java.time.LocalDate;
import java.util.Collections;

public class FakeClient {

    public static Client getFirstClient(){
        return new Client(1L,
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static Client getSavedFirstClient(){
        return new Client(1L,
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }

    public static Client getSecondClient(){
        return new Client(2L,
                "Pavel",
                "Sergeevich",
                "Sergeev",
                "pavel@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static ClientDto getFirstClientDto(){
        return new ClientDto(1L,
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }

    public static ClientDto getSavedFirstClientDto(){
        return new ClientDto(1L,
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static ClientDto getSecondClientDto(){
        return new ClientDto(2L,
                "Pavel",
                "Sergeevich",
                "Sergeev",
                "pavel@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static Client getThirdClient(){
        return new Client(3L,
                "Anna",
                "Dmitreeva",
                "Rabinovich",
                "anna@gmail.com",
                Role.CUSTOMER_USER);
    }

    public static ClientResponse getFirstClientResponse(){
        return new ClientResponse(1L,
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static ClientResponse getSecondClientResponse(){
        return new ClientResponse(2L,
                "Pavel",
                "Sergeevich",
                "Sergeev",
                "pavel@gmail.com",
                Role.CUSTOMER_USER);
    }

    public static ClientRequest getFirstClientRequest(){
        return new ClientRequest(
                "Ivan",
                "Ivanovich",
                "Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }

    public static ClientResponseWithFullName getFirstClientResponseWithFullName(){
        return new ClientResponseWithFullName(
                "Ivan Ivanovich Ivanov",
                "ivan@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static ClientResponseWithFullName getSecondClientResponseWithFullName(){
        return new ClientResponseWithFullName(
                "Pavel Sergeevich Sergeev",
                "pavel@gmail.com",
                Role.CUSTOMER_USER);
    }
    public static ClientResponseWithFullName getThirdClientResponseWithFullName(){
        return new ClientResponseWithFullName(
                "Anna Dmitreeva Rabinovich",
                "anna@gmail.com",
                Role.CUSTOMER_USER);
    }
}
