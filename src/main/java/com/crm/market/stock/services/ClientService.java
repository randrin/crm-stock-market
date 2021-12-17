package com.crm.market.stock.services;

import com.crm.market.stock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto findById(Integer id);

    ClientDto findByMail(String mailClient);

    List<ClientDto> findAll();

    void deleteById(Integer id);
}
