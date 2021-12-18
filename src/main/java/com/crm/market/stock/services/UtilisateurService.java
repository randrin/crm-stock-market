package com.crm.market.stock.services;

import com.crm.market.stock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByEmail(String mailUser);

    List<UtilisateurDto> findAll();

    void deleteById(Integer id);
}
