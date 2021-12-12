package com.crm.market.stock.services;

import com.crm.market.stock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto ventesDto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void deleteById(Integer id);
}
