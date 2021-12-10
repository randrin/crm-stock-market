package com.crm.market.stock.validator;

import com.crm.market.stock.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate(RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();

        if(rolesDto == null) {
            errors.add("Veuillez renseigner le nom du role.");
            errors.add("Veuillez renseigner le nom de l'utilisateur.");
        }

        if (!StringUtils.hasLength(rolesDto.getRoleName())) {
            errors.add("Veuillez renseigner le nom du role.");
        }
        if (!StringUtils.hasLength(rolesDto.getUtilisateur().getNom())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur.");
        }

        return errors;
    }
}
