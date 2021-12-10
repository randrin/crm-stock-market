package com.crm.market.stock.validator;

import com.crm.market.stock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if(entrepriseDto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise.");
            errors.add("Veuillez renseigner la description de l'entreprise.");
            errors.add("Veuillez renseigner l'addresse de l'entreprise.");
            errors.add("Veuillez renseigner le code fiscale de l'entreprise.");
            errors.add("Veuillez renseigner la photo de l'entreprise.");
            errors.add("Veuillez renseigner l'email de l'entreprise.");
            errors.add("Veuillez renseigner le numéro de téléphone de l'entreprise.");
            errors.add("Veuillez renseigner le site web de l'entreprise.");
            errors.add("Veuillez selectionner un utilisateur.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'entreprise.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getDescription())) {
            errors.add("Veuillez renseigner la description de l'entreprise.");
        }
        if(entrepriseDto.getAddresse() == null) {
            errors.add("Veuillez renseigner l'addresse de l'entreprise.");
        } else {
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getAddresseOne())) {
                errors.add("Le champ addresse one est obbligatoire.");
            }
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getCity())) {
                errors.add("Le champ city est obbligatoire.");
            }
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getCodePostale())) {
                errors.add("Le champ code postale est obbligatoire.");
            }
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getState())) {
                errors.add("Le champ state est obbligatoire.");
            }
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getCountry())) {
                errors.add("Le champ country est obbligatoire.");
            }
            if(!StringUtils.hasLength(entrepriseDto.getAddresse().getZipCode())) {
                errors.add("Le champ zip code est obbligatoire.");
            }
        }
        if(!StringUtils.hasLength(entrepriseDto.getCodeFiscale())) {
            errors.add("Veuillez renseigner le code fiscale de l'entreprise.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo de l'entreprise.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'entreprise.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getNumTel())) {
            errors.add("Veuillez renseigner le numéro de téléphone de l'entreprise.");
        }
        if(!StringUtils.hasLength(entrepriseDto.getSiteWeb())) {
            errors.add("Veuillez renseigner le site web de l'entreprise.");
        }
        if(entrepriseDto.getUtilisateurs().stream().collect(Collectors.counting()) == 0) {
            errors.add("Veuillez selectionner un utilisateur.");
        }

        return errors;
    }
}
