package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.CommandeClientDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Order Client API")
public interface CommandeClientApi {

    @PostMapping(value = Constants.API_ORDER + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Order Client", notes = "Api to save order client in store", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Client save successfully."),
            @ApiResponse(code = 400, message = "Order Client not valid.")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = Constants.API_ORDER + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Order Client By ID", notes = "Api to find order client by id in store", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Client found successfully by ID."),
            @ApiResponse(code = 404, message = "Order Client not found.")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_ORDER + "/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Order Client By CODE", notes = "Api to find order client by code in store", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Client found successfully by CODE."),
            @ApiResponse(code = 404, message = "Order Client not found.")
    })
    ResponseEntity<CommandeClientDto> findByCodeClient(@PathVariable String code);

    @GetMapping(value = Constants.API_ROOT + "/client/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Orders Client", notes = "Api to get all orders client in store", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Order(s) client found successfully."),
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = Constants.API_ORDER + "/delete/id={id}")
    @ApiOperation(value = "Delete Order Client by ID", notes = "Api to delete order client by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Client deleted successfully by ID."),
    })
    ResponseEntity deleteById(@PathVariable Integer id);

}
