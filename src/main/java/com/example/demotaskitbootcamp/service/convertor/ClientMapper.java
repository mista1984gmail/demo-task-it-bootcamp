package com.example.demotaskitbootcamp.service.convertor;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.web.controller.request.ClientRequest;
import com.example.demotaskitbootcamp.web.response.ClientResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

  Client dtoToModel(ClientDto clientDto);

  ClientDto modelToDto(Client client);

  List<ClientDto> toListDto(List<Client> clients);
  ClientDto requestToDto(ClientRequest clientRequest);
  ClientRequest dtoToRequest(ClientDto clientDto);
  Client requestToModel(ClientRequest clientRequest);

  ClientResponse dtoToResponse(ClientDto clientDto);

}
