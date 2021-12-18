package com.crm.market.stock.services;

import com.crm.market.stock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Integer id);

    EntrepriseDto findByEmail(String mailEntreprise);

    List<EntrepriseDto> findAll();

    void deleteById(Integer id);
}
