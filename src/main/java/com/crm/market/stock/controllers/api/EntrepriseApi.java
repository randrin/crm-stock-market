package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.EntrepriseDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Business API")
public interface EntrepriseApi {

    @PostMapping(value = Constants.API_BUSINESS + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Business", notes = "Api to save business in store", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Business save successfully."),
            @ApiResponse(code = 400, message = "Business not valid.")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = Constants.API_BUSINESS + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Business By ID", notes = "Api to find business by id in store", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Business found successfully by ID."),
            @ApiResponse(code = 404, message = "Business not found.")
    })
    EntrepriseDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_BUSINESS + "/mail={mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Business By Mail", notes = "Api to find business by mail in store", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Business found successfully by MAIL."),
            @ApiResponse(code = 404, message = "Business not found.")
    })
    EntrepriseDto findByEmail(@PathVariable String mail);

    @GetMapping(value = Constants.API_ROOT + "/business", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Business", notes = "Api to get all business in store", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Business found successfully."),
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = Constants.API_BUSINESS + "/delete/id={id}")
    @ApiOperation(value = "Delete Business by ID", notes = "Api to delete business by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Business deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
