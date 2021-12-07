package com.crm.market.stock.validator;

import com.crm.market.stock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null) {
            errors.add("Veuillez renseigner le nom de l'utilisateur.");
            errors.add("Veuillez renseigner l'addresse de l'utilisateur.");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur.");
            errors.add("Veuillez renseigner l'email de l'utilisateur.");
            errors.add("Veuillez renseigner la date de naissance de l'utilisateur.");
            errors.add("Veuillez renseigner la photo de l'utilisateur.");
        }
        if(!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur.");
        }
        if(!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prnom de l'utilisateur.");
        }
        if(!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'utilisateur.");
        }
        if(utilisateurDto.getDateDeNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance de l'utilisateur.");
        }
        if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur.");
        }
        if(utilisateurDto.getAddresse() == null) {
            errors.add("Veuillez renseigner l'addresse de l'utilisateur.");
        } else {
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getAddresseOne())) {
                errors.add("Le champ addresse one est obbligatoire.");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getCity())) {
                errors.add("Le champ city est obbligatoire.");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getCodePostale())) {
                errors.add("Le champ code postale est obbligatoire.");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getState())) {
                errors.add("Le champ state est obbligatoire.");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getCountry())) {
                errors.add("Le champ country est obbligatoire.");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAddresse().getZipCode())) {
                errors.add("Le champ zip code est obbligatoire.");
            }
        }
        if(StringUtils.hasLength(utilisateurDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo de l'utilisateur.");
        }
        if(StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'utilisateur'.");
        }
        if(StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'utilisateur'.");
        }
        return errors;
    }
}
