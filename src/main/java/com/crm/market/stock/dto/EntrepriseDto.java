package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import com.crm.market.stock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private AddresseDto addresse;

    private String codeFiscale;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
            // TODO throws on ecxeption
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .addresse(AddresseDto.fromEntity(entreprise.getAddresse()))
                .codeFiscale(entreprise.getCodeFiscale())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .utilisateurs(entreprise.getUtilisateurs() != null ?
                        entreprise.getUtilisateurs().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if(entrepriseDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setAddresse(AddresseDto.toEntity(entrepriseDto.getAddresse()));
        entreprise.setCodeFiscale(entrepriseDto.getCodeFiscale());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNom(entrepriseDto.getNumTel());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
        entreprise.setUtilisateurs(entrepriseDto.getUtilisateurs() != null ?
                entrepriseDto.getUtilisateurs().stream().map(UtilisateurDto::toEntity).collect(Collectors.toList()) : null);

        return entreprise;
    }
}
