package com.crm.market.stock.controllers;

import com.crm.market.stock.controllers.api.EntrepriseApi;
import com.crm.market.stock.dto.EntrepriseDto;
import com.crm.market.stock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return entrepriseService.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto findByEmail(String mail) {
        return entrepriseService.findByEmail(mail);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        entrepriseService.deleteById(id);
    }
}
