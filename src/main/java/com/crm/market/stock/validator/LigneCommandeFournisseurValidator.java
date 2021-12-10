package com.crm.market.stock.validator;

import com.crm.market.stock.dto.LigneCommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeFournisseurDto == null) {
            errors.add("Veuillez renseigner le code de l'article.");
            errors.add("Veuillez renseigner le code commande fournisseur.");
            errors.add("Veuillez renseigner la quantité de la commande.");
            errors.add("Veuillez renseigner le prix unitaire de la commande.");
        }
        if (!StringUtils.hasLength(ligneCommandeFournisseurDto.getArticle().getCodeArticle())) {
            errors.add("Veuillez renseigner le code de l'article.");
        }
        if (!StringUtils.hasLength(ligneCommandeFournisseurDto.getCommandeFournisseur().getCode())) {
            errors.add("Veuillez renseigner le code commande fournisseur.");
        }
        if (ligneCommandeFournisseurDto.getQuantite().intValue() == 0) {
            errors.add("Veuillez renseigner la quantité de la commande.");
        }
        if (ligneCommandeFournisseurDto.getPrixUnitaire().intValue() == 0) {
            errors.add("Veuillez renseigner le prix unitaire de la commande.");
        }

        return errors;
    }
}
