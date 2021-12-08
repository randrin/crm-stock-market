package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import com.crm.market.stock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AddresseDto addresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if(fournisseur == null) {
            return null;
            // TODO throws on ecxeption
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .addresse(AddresseDto.fromEntity(fournisseur.getAddresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .commandeFournisseurs(fournisseur.getCommandeFournisseurs() != null
                        ? fournisseur.getCommandeFournisseurs().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if(fournisseurDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAddresse(AddresseDto.toEntity(fournisseurDto.getAddresse()));
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setCommandeFournisseurs(fournisseurDto.getCommandeFournisseurs() != null ?
                fournisseurDto.getCommandeFournisseurs().stream().map(CommandeFournisseurDto::toEntity).collect(Collectors.toList()) : null);

        return fournisseur;
    }
}
