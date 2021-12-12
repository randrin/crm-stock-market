package com.crm.market.stock.dto;

import com.crm.market.stock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    private Integer idEntreprise;

    public static CategoryDto fromEntity(Category category) {
        if(category == null) {
            return null;
            // TODO throws on ecxeption
        }
        return CategoryDto.builder()
                .id(category.getId())
                .designation(category.getDesignation())
                .code(category.getCode())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if(categoryDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEntreprise());

        return category;
    }
}
