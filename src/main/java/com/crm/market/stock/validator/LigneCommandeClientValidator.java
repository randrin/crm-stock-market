package com.crm.market.stock.validator;

import com.crm.market.stock.dto.LigneCommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null) {
            errors.add("Veuillez renseigner le code de l'article.");
            errors.add("Veuillez renseigner le code commande client.");
            errors.add("Veuillez renseigner la quantité de la commande.");
            errors.add("Veuillez renseigner le prix unitaire de la commande.");
        }
        if (!StringUtils.hasLength(ligneCommandeClientDto.getArticle().getCodeArticle())) {
            errors.add("Veuillez renseigner le code de l'article.");
        }
        if (!StringUtils.hasLength(ligneCommandeClientDto.getCommandeClient().getCode())) {
            errors.add("Veuillez renseigner le code commande client.");
        }
        if (ligneCommandeClientDto.getQuantite().intValue() == 0) {
            errors.add("Veuillez renseigner la quantité de la commande.");
        }
        if (ligneCommandeClientDto.getPrixUnitaire().intValue() == 0) {
            errors.add("Veuillez renseigner le prix unitaire de la commande.");
        }

        return errors;
    }
}
