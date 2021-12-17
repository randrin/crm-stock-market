package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.CommandeFournisseurDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Order Provider API")
public interface CommandeFournisseurApi {

    @PostMapping(value = Constants.API_PROVIDER + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Order Provider", notes = "Api to save order provider in store", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Provider save successfully."),
            @ApiResponse(code = 400, message = "Order Provider not valid.")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = Constants.API_PROVIDER + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Order Provider By ID", notes = "Api to find order provider by id in store", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Provider found successfully by ID."),
            @ApiResponse(code = 404, message = "Order Provider not found.")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_PROVIDER + "/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Order Provider By CODE", notes = "Api to find order provider by code in store", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Provider found successfully by CODE."),
            @ApiResponse(code = 404, message = "Order Provider not found.")
    })
    ResponseEntity<CommandeFournisseurDto> findByCodeFournisseur(@PathVariable String code);

    @GetMapping(value = Constants.API_ROOT + "/provider/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Orders Provider", notes = "Api to get all orders provider in store", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Order(s) provider found successfully."),
    })
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping(value = Constants.API_PROVIDER + "/delete/id={id}")
    @ApiOperation(value = "Delete Order Provider by ID", notes = "Api to delete order provider by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Provider deleted successfully by ID."),
    })
    ResponseEntity deleteById(@PathVariable Integer id);
}
