package com.example.demotaskitbootcamp.web.controller;

import com.example.demotaskitbootcamp.exception.ClientWithEmailExistException;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.service.dto.ClientDto;
import com.example.demotaskitbootcamp.web.request.ClientRequest;
import com.example.demotaskitbootcamp.web.response.ClientResponse;
import com.example.demotaskitbootcamp.web.response.ClientResponseWithFullName;
import com.example.demotaskitbootcamp.web.validator.EmailValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    private final EmailValidator emailValidator;

    @Operation(summary = "Save client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save client", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse save(@Valid @RequestBody ClientRequest clientRequest, BindingResult emailBinding) {
        log.info("Validate client by unique email");
        emailValidator.validate(clientRequest, emailBinding);
        if (emailBinding.hasErrors()) {
            throw new ClientWithEmailExistException("Client with email " + clientRequest.getEmail() + " exist");
        }
        log.info("Save client {}", clientRequest);
        return clientMapper.dtoToResponse(clientService.save(clientMapper.requestToDto(clientRequest)));
    }

    @Operation(summary = "Find all clients with pagination and sorting by different fields")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find clients", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> findAll(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "clients_per_page", required = false) Integer clientsPerPage,
                                   @RequestParam(value = "sort_by", required = false) String sortBy) {
        log.info("Find all clients");
        if (page == null || clientsPerPage == null) {
            return clientMapper.toListResponse(clientService.findAll());
        } else {
            return clientMapper.toListResponse(clientService.findWithPagination(page, clientsPerPage, sortBy));
        }
    }
    @Operation(summary = "Find all clients with pagination and sorting by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find clients", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseWithFullName.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/sortByEmail")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponseWithFullName> findAllAndSortByEmail(@RequestParam(value = "page", required = false) Integer page) {
        log.info("Find all clients sorting by email");
            return clientService.findAllAndSortByEmail(page);
    }

}
