package com.crm.market.stock.dto;

import com.crm.market.stock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneVenteDto> ligneVentes;

    private Integer idEntreprise;

    public static VentesDto fromEntity(Ventes ventes) {
        if(ventes == null) {
            return null;
            // TODO throws on ecxeption
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .ligneVentes(ventes.getLigneVentes() != null ?
                        ventes.getLigneVentes().stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList()) : null)
                .idEntreprise(ventes.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto) {
        if(ventesDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setCode(ventesDto.getCode());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setLigneVentes(ventesDto.getLigneVentes() != null ?
                ventesDto.getLigneVentes().stream().map(LigneVenteDto::toEntity).collect(Collectors.toList()) : null);
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());

        return ventes;
    }
}
