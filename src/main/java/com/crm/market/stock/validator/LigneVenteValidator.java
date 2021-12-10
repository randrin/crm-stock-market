package com.crm.market.stock.validator;

import com.crm.market.stock.dto.LigneVenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto ligneVenteDto) {
        List<String> errors = new ArrayList<>();

        if(ligneVenteDto == null) {
            errors.add("Veuillez renseigner le code vente.");
            errors.add("Veuillez renseigner la quantité de la vente.");
            errors.add("Veuillez renseigner le prix unitaire de la vente.");
        }
        if (!StringUtils.hasLength(ligneVenteDto.getVente().getCode())) {
            errors.add("Veuillez renseigner le code vente.");
        }
        if (ligneVenteDto.getQuantite().intValue() == 0) {
            errors.add("Veuillez renseigner la quantité de la vente.");
        }
        if (ligneVenteDto.getPrixUnitaire().intValue() == 0) {
            errors.add("Veuillez renseigner le prix unitaire de la vente.");
        }

        return errors;
    }
}
