package com.crm.market.stock.dto;

import com.crm.market.stock.model.Category;
import com.crm.market.stock.model.LigneVente;
import com.crm.market.stock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if(ligneVente == null) {
            return null;
            // TODO throws on ecxeption
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(VentesDto.fromEntity(ligneVente.getVente()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaite())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if(ligneVenteDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaite(ligneVenteDto.getPrixUnitaire());

        return ligneVente;
    }
}
