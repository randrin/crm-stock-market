package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.CategoryDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.services.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void saveTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1)
                .code("1234")
                .designation("category designation")
                .idEntreprise(1)
                .build();

        CategoryDto categoryDtoSaved = categoryService.save(categoryDto);

        Assertions.assertNotNull(categoryDtoSaved);
        Assertions.assertNotNull(categoryDtoSaved.getId());
        Assertions.assertEquals(categoryDtoSaved.getCode(), categoryDto.getCode());
        Assertions.assertEquals(categoryDtoSaved.getDesignation(), categoryDto.getDesignation());
        Assertions.assertEquals(categoryDtoSaved.getIdEntreprise(), categoryDto.getIdEntreprise());
    }

    @Test
    public void findByIdTest() {
        CategoryDto categoryDto = categoryService.findById(2);
        Assertions.assertNotNull(categoryDto);
    }

    @Test
    public void findByCodeTest() {
        CategoryDto categoryDto = categoryService.findByCode("1234");
        Assertions.assertNotNull(categoryDto);
    }

    @Test
    public void findAllTest() {
        List<CategoryDto> listCategoryDto = categoryService.findAll();
        Assertions.assertTrue(listCategoryDto.size() != 0);
    }

    @Test
    public void deleteById() {
        CategoryDto categoryDto = categoryService.findByCode("1234");
        categoryService.deleteById(categoryDto.getId());
    }

    @Test
    public void shouldThrowInvalidEntityException() {
        CategoryDto categoryDto = CategoryDto.builder().build();

        InvalidEntityException expectedException = Assertions.assertThrows(InvalidEntityException.class, () -> categoryService.save(categoryDto));

        Assertions.assertEquals(expectedException.getErrorCodes(), ErrorCodes.CATEGORY_NOT_VALID);
        Assertions.assertEquals(1, expectedException.getErrors().size());
        Assertions.assertEquals(expectedException.getErrors().get(0), "Veuillez renseigner le code de la category.");
    }

    @Test
    public void shouldThrowEntityNotFoundException1() {

        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> categoryService.findById(0));

        Assertions.assertEquals(expectedException.getErrorCodes(), ErrorCodes.CATEGORY_NOT_FOUND);
        Assertions.assertEquals("Not found category with ID= 0", expectedException.getMessage());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        categoryService.findById(0);
    }
}