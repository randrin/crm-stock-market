package com.crm.market.stock.dto;

import com.crm.market.stock.dto.common.AddresseDto;
import com.crm.market.stock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    private Integer idEntreprise;

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
                .commandeClients(client.getCommandeClients() != null
                        ? client.getCommandeClients().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList()) : null)
                .idEntreprise(client.getIdEntreprise())
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if(clientDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAddresse(AddresseDto.toEntity(clientDto.getAddresse()));
        client.setPhoto(clientDto.getPhoto());
        client.setMail(clientDto.getMail());
        client.setNumTel(clientDto.getNumTel());
        client.setCommandeClients(clientDto.getCommandeClients() != null ?
                clientDto.getCommandeClients().stream().map(CommandeClientDto::toEntity).collect(Collectors.toList()) : null);
        client.setIdEntreprise(client.getIdEntreprise());

        return client;
    }
}
