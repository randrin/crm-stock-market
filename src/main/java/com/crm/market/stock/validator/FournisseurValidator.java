package com.crm.market.stock.validator;

import com.crm.market.stock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if(fournisseurDto == null) {
            errors.add("Veuillez renseigner le nom du fourmisseur.");
            errors.add("Veuillez renseigner le prénom du fournisseur.");
            errors.add("Veuillez renseigner la photo du fournisseur.");
            errors.add("Veuillez renseigner l'email du fournisseur.");
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur.");
        }
        if(!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner le nom du fourmisseur.");
        }
        if(!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom du fournisseur.");
        }
        if(fournisseurDto.getAddresse() == null) {
            errors.add("Veuillez renseigner l'addresse du fournisseur.");
        } else {
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getAddresseOne())) {
                errors.add("Le champ addresse one est obbligatoire.");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getCity())) {
                errors.add("Le champ city est obbligatoire.");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getCodePostale())) {
                errors.add("Le champ code postale est obbligatoire.");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getState())) {
                errors.add("Le champ state est obbligatoire.");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getCountry())) {
                errors.add("Le champ country est obbligatoire.");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAddresse().getZipCode())) {
                errors.add("Le champ zip code est obbligatoire.");
            }
        }
        if(!StringUtils.hasLength(fournisseurDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo du fournisseur.");
        }
        if(!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner l'email du fournisseur.");
        }
        if(!StringUtils.hasLength(fournisseurDto.getNumTel())) {
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur.");
        }

        return errors;
    }
}
