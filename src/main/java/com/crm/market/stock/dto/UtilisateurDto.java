package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import com.crm.market.stock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if(utilisateur == null) {
            return null;
            // TODO throws on ecxeption
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .addresse(AddresseDto.fromEntity(utilisateur.getAddresse()))
                .photo(utilisateur.getPhoto())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .roles(utilisateur.getRoles() != null
                        ? utilisateur.getRoles().stream().map(RolesDto::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if(utilisateurDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setAddresse(AddresseDto.toEntity(utilisateurDto.getAddresse()));
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        utilisateur.setRoles(utilisateurDto.getRoles() != null ?
                utilisateurDto.getRoles().stream().map(RolesDto::toEntity).collect(Collectors.toList()) : null);

        return utilisateur;
    }
}
