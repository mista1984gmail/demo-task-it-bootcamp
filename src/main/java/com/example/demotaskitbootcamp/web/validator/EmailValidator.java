package com.example.demotaskitbootcamp.web.validator;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import com.example.demotaskitbootcamp.service.ClientService;
import com.example.demotaskitbootcamp.service.convertor.ClientMapper;
import com.example.demotaskitbootcamp.web.controller.request.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    @Autowired
    public EmailValidator(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client person = clientMapper.requestToModel((ClientRequest) o);

        if (clientService.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "", "A person with this email already exists");
    }
}
