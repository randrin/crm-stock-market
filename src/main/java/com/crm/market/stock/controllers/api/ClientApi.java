package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.ClientDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "Client API")
public interface ClientApi {

    @PostMapping(value = Constants.API_CLIENT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Client", notes = "Api to save client in store", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client save successfully."),
            @ApiResponse(code = 400, message = "Client not valid.")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = Constants.API_CLIENT + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Client By ID", notes = "Api to find client by id in store", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client found successfully by ID."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    ClientDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_CLIENT + "/mail={mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Client By Mail", notes = "Api to find client by mail in store", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client found successfully by MAIL."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    ClientDto findByMail(@PathVariable String mail);

    @GetMapping(value = Constants.API_ROOT + "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Clients", notes = "Api to get all clients in store", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Client(s) found successfully."),
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = Constants.API_CLIENT + "/delete/id={id}")
    @ApiOperation(value = "Delete Client by ID", notes = "Api to delete client by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
