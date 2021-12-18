package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.VentesDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Sales API")
public interface VentesApi {

    @PostMapping(value = Constants.API_SALES + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Sales", notes = "Api to save sale in store", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sales save successfully."),
            @ApiResponse(code = 400, message = "Sales not valid.")
    })
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = Constants.API_SALES + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Sales By ID", notes = "Api to find sale by id in store", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sales found successfully by ID."),
            @ApiResponse(code = 404, message = "Sales not found.")
    })
    VentesDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_SALES + "/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Sales By Code", notes = "Api to find sale by code in store", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sales found successfully by CODE."),
            @ApiResponse(code = 404, message = "Sales not found.")
    })
    VentesDto findByCode(@PathVariable String code);

    @GetMapping(value = Constants.API_ROOT + "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Sales", notes = "Api to get all sales in store", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Sale(s) found successfully."),
    })
    List<VentesDto> findAll();

    @DeleteMapping(value = Constants.API_SALES + "/delete/id={id}")
    @ApiOperation(value = "Delete Sales by ID", notes = "Api to delete sale by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sales deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
