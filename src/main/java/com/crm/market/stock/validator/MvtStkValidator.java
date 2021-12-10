package com.crm.market.stock.validator;

import com.crm.market.stock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();

        if(mvtStkDto == null) {
            errors.add("Veuillez renseigner la date du mvt stock.");
            errors.add("Veuillez renseigner la quantité du mvt stock.");
            errors.add("Veuillez renseigner le code article du mvt stock.");
            errors.add("Veuillez renseigner le type du mvt stock.");
        }
        if (!StringUtils.hasLength(mvtStkDto.getDateMvt().toString())) {
            errors.add("Veuillez renseigner la date du mvt stock.");
        }
        if (mvtStkDto.getQuantite().intValue() == 0) {
            errors.add("Veuillez renseigner la quantité du mvt stock.");
        }
        if (!StringUtils.hasLength(mvtStkDto.getArticle().getCodeArticle())) {
            errors.add("Veuillez renseigner le code article du mvt stock.");
        }
        if (!StringUtils.hasLength(mvtStkDto.getTypeMvtStk().toString())) {
            errors.add("Veuillez renseigner le type du mvt stock.");
        }

        return errors;
    }
}
