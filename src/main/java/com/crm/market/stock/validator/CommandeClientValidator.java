package com.crm.market.stock.validator;

import com.crm.market.stock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();

        if(commandeClientDto == null) {
            errors.add("Veuillez renseigner le code de la commande client.");
            errors.add("Veuillez renseigner la date de la commande client.");
            errors.add("Veuillez renseigner le nom du client.");
            errors.add("La commande client doit contenir au moins 1 article.");
            return errors;
        }

        if(!StringUtils.hasLength(commandeClientDto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande client.");
        }
        if(commandeClientDto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande client.");
        }
        if(!StringUtils.hasLength(commandeClientDto.getClient().getNom())) {
            errors.add("Veuillez renseigner le nom du client.");
        }
        if(commandeClientDto.getLigneCommandeClients().stream().collect(Collectors.counting()) == 0) {
            errors.add("La commande client doit contenir au moins 1 article.");
        }

        return errors;
    }
}
