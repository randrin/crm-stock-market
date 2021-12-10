package com.crm.market.stock.validator;

import com.crm.market.stock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VentesValidator {

    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if(ventesDto == null) {
            errors.add("Veuillez renseigner le code de vente.");
            errors.add("La ligne ventes ne doit pas etre vide.");
        }
        if(!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("Veuillez renseigner le code de vente.");
        }
        if(ventesDto.getLigneVentes().stream().collect(Collectors.counting()) == 0) {
            errors.add("La ligne ventes ne doit pas etre vide.");
        }

        return errors;
    }
}
