package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.CategoryDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Category;
import com.crm.market.stock.repository.CategoryRepository;
import com.crm.market.stock.services.CategoryService;
import com.crm.market.stock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);

        if(!errors.isEmpty()) {
            log.error("Not valid category {}", categoryDto);
            throw new InvalidEntityException("Not valid category", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id == null) {
            log.error("Not category found id=", + id + "{}");
            return null;
        }
        Optional<Category> category = categoryRepository.findById(id);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() -> new EntityNotFoundException("Not found category with ID= " +id,
                ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String codeCategory) {
        if(!StringUtils.hasLength(codeCategory)) {
            log.error("Not category found code=" + codeCategory + "{}");
            return null;
        }
        Optional<Category> category = categoryRepository.findByCode(codeCategory);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() -> new EntityNotFoundException("Not found category with CODE= " +codeCategory,
                ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null || !categoryRepository.existsById(id)) {
            log.error("Not found category with id=", + id + "{}");
            throw new EntityNotFoundException("Not found category with ID= " +id,
                    ErrorCodes.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }
}
