package com.crm.market.stock.services;

import com.crm.market.stock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);

    FournisseurDto findByMail(String mailProvider);

    List<FournisseurDto> findAll();

    void deleteById(Integer id);
}
