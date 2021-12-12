package com.crm.market.stock.dto;

import com.crm.market.stock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDto {

    private Integer id;

    private String roleName;

    private UtilisateurDto utilisateur;

    private Integer idEntreprise;

    public static RolesDto fromEntity(Roles roles) {
        if(roles == null) {
            return null;
            // TODO throws on ecxeption
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .idEntreprise(roles.getIdEntreprise())
                .build();
    }

    public static Roles toEntity(RolesDto rolesDto) {
        if(rolesDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));
        roles.setIdEntreprise(rolesDto.getIdEntreprise());

        return roles;
    }
}
