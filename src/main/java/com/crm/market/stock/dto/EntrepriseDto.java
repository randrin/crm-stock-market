package com.crm.market.stock.dto;

import com.crm.market.stock.model.Utilisateur;
import com.crm.market.stock.model.common.Addresse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private Addresse addresse;

    private String codeFiscale;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private List<Utilisateur> utilisateurs;
}
