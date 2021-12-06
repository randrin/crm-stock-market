package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String dateDeNaissance;

    private String motDePasse;

    private AddresseDto addresse;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;
}
