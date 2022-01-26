package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.ClientDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Client;
import com.crm.market.stock.repository.ClientRepository;
import com.crm.market.stock.services.ClientService;
import com.crm.market.stock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);

        if(!errors.isEmpty()) {
            log.error("Not valid client {}", clientDto);
            throw new InvalidEntityException("Not valid client", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null) {
            log.error("Not client found id=", + id + "{}");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);

        ClientDto clientDto = ClientDto.fromEntity(client.get());

        return Optional.of(clientDto).orElseThrow(() -> new EntityNotFoundException("Not found client with ID= " +id,
                ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public ClientDto findByMail(String mailClient) {
        if(!StringUtils.hasLength(mailClient)) {
            log.error("Not client found with mail=" + mailClient + "{}");
            return null;
        }
        Optional<Client> client = clientRepository.findByMail(mailClient);

        ClientDto clientDto = ClientDto.fromEntity(client.get());

        return Optional.of(clientDto).orElseThrow(() -> new EntityNotFoundException("Not found client with MAIL= " +mailClient,
                ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null || !clientRepository.existsById(id)) {
            log.error("Not found client with id=", + id + "{}");
            throw new EntityNotFoundException("Not found client with ID= " +id,
                    ErrorCodes.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }
}
