package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.CategoryDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Category API")
public interface CategoryApi {

    @PostMapping(value = Constants.API_ROOT + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Category", notes = "Api to save category in store", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category save successfully."),
            @ApiResponse(code = 400, message = "Category not valid.")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = Constants.API_ROOT + "/category/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Category By ID", notes = "Api to find category by id in store", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category found successfully by ID."),
            @ApiResponse(code = 404, message = "Category not found.")
    })
    CategoryDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_ROOT + "/category/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Category By CODE", notes = "Api to find category by code in store", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category found successfully by CODE."),
            @ApiResponse(code = 404, message = "Category not found.")
    })
    CategoryDto findByCode(@PathVariable String code);

    @GetMapping(value = Constants.API_ROOT + "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Categories", notes = "Api to get all categories in store", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Category(ies) found successfully."),
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = Constants.API_ROOT + "/category/delete/id={id}")
    @ApiOperation(value = "Delete Category by ID", notes = "Api to delete category by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
