package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import com.crm.market.stock.model.Category;
import com.crm.market.stock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AddresseDto addresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client) {
        if(client == null) {
            return null;
            // TODO throws on ecxeption
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .addresse(AddresseDto.fromEntity(client.getAddresse()))
                .photo(client.getPhoto())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                //.commandeClients(client.getCommandesClient() != null
                //        ? client.getCommandesClient().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }
}
