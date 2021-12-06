package com.crm.market.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private String code;

    private List<LigneVenteDto> ligneVentes;
}
