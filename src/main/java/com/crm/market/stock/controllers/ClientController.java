package com.crm.market.stock.controllers;

import com.crm.market.stock.controllers.api.ClientApi;
import com.crm.market.stock.dto.ClientDto;
import com.crm.market.stock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public ClientDto findByMail(String mailClient) { return clientService.findByMail(mailClient); }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        clientService.deleteById(id);
    }
}
