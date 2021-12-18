package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.FournisseurDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Provider API")
public interface FournisseurApi {
    @PostMapping(value = Constants.API_PROVIDER + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Provider", notes = "Api to save provider in store", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Provider save successfully."),
            @ApiResponse(code = 400, message = "Provider not valid.")
    })
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = Constants.API_PROVIDER + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Provider By ID", notes = "Api to find provider by id in store", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Provider found successfully by ID."),
            @ApiResponse(code = 404, message = "Provider not found.")
    })
    FournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_PROVIDER + "/mail={mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Provider By Mail", notes = "Api to find provider by mail in store", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Provider found successfully by MAIL."),
            @ApiResponse(code = 404, message = "Provider not found.")
    })
    FournisseurDto findByMail(@PathVariable String mail);

    @GetMapping(value = Constants.API_ROOT + "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Providers", notes = "Api to get all providers in store", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Provider(s) found successfully."),
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = Constants.API_PROVIDER + "/delete/id={id}")
    @ApiOperation(value = "Delete Provider by ID", notes = "Api to delete provider by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Provider deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
