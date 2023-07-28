package com.example.demotaskitbootcamp.service.convertor;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

  Client dtoToModel(ClientDto clientDto);

  ClientDto modelToDto(Client client);

  List<ClientDto> toListDto(List<Client> clients);

}
