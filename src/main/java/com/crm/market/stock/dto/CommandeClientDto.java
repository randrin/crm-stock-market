package com.crm.market.stock.dto;

import com.crm.market.stock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private Integer idEntreprise;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if(commandeClient == null) {
            return null;
            // TODO throws on ecxeption
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .ligneCommandeClients(commandeClient.getLigneCommandeClients() != null ?
                        commandeClient.getLigneCommandeClients().stream().map(LigneCommandeClientDto::fromEntity).collect(Collectors.toList()) : null)
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if(commandeClientDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
        commandeClient.setLigneCommandeClients(commandeClientDto.getLigneCommandeClients() != null ?
                commandeClientDto.getLigneCommandeClients().stream().map(LigneCommandeClientDto::toEntity).collect(Collectors.toList()) : null);
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());

        return commandeClient;
    }
}
