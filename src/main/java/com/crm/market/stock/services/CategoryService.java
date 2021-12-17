package com.crm.market.stock.services;

import com.crm.market.stock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Integer id);

    CategoryDto findByCode(String codeCategory);

    List<CategoryDto> findAll();

    void deleteById(Integer id);
}
