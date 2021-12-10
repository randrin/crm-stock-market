package com.crm.market.stock.validator;

import com.crm.market.stock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if(commandeFournisseurDto == null) {
            errors.add("Veuillez renseigner le code de la commande fournisseur.");
            errors.add("Veuillez renseigner la date de la commande fournisseur.");
            errors.add("Veuillez renseigner le nom du fournisseur.");
            errors.add("La commande fournisseur doit contenir au moins 1 article.");
            return errors;
        }

        if(!StringUtils.hasLength(commandeFournisseurDto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande fournisseur.");
        }
        if(commandeFournisseurDto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande fournisseur.");
        }
        if(!StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getNom())) {
            errors.add("Veuillez renseigner le nom du fournisseur.");
        }
        if(commandeFournisseurDto.getLigneCommandeFournisseurs().stream().collect(Collectors.counting()) == 0) {
            errors.add("La commande fournisseur doit contenir au moins 1 article.");
        }

        return errors;
    }
}
