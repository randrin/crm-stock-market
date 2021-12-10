package com.crm.market.stock.validator;

import com.crm.market.stock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if(clientDto == null) {
            errors.add("Veuillez renseigner le nom du client.");
            errors.add("Veuillez renseigner le prnom du client.");
            errors.add("Veuillez renseigner l'addresse du client.");
            errors.add("Veuillez renseigner la photo du client.");
            errors.add("Veuillez renseigner l'email du client.");
            errors.add("Veuillez renseigner le numéro de téléphone du client.");
            return errors;
        }

        if(!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Veuillez renseigner le nom du client.");
        }
        if(!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Veuillez renseigner le prnom du client.");
        }
        if(clientDto.getAddresse() == null) {
            errors.add("Veuillez renseigner l'addresse du client.");
        } else {
            if(!StringUtils.hasLength(clientDto.getAddresse().getAddresseOne())) {
                errors.add("Le champ addresse one est obbligatoire.");
            }
            if(!StringUtils.hasLength(clientDto.getAddresse().getCity())) {
                errors.add("Le champ city est obbligatoire.");
            }
            if(!StringUtils.hasLength(clientDto.getAddresse().getCodePostale())) {
                errors.add("Le champ code postale est obbligatoire.");
            }
            if(!StringUtils.hasLength(clientDto.getAddresse().getState())) {
                errors.add("Le champ state est obbligatoire.");
            }
            if(!StringUtils.hasLength(clientDto.getAddresse().getCountry())) {
                errors.add("Le champ country est obbligatoire.");
            }
            if(!StringUtils.hasLength(clientDto.getAddresse().getZipCode())) {
                errors.add("Le champ zip code est obbligatoire.");
            }
        }
        if(!StringUtils.hasLength(clientDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo du client.");
        }
        if(!StringUtils.hasLength(clientDto.getMail())) {
            errors.add("Veuillez renseigner l'email du client.");
        }
        if(!StringUtils.hasLength(clientDto.getNumTel())) {
            errors.add("Veuillez renseigner le numéro de téléphone du client.");
        }

        return errors;
    }
}
