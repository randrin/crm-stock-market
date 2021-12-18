package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.UtilisateurDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "User API")
public interface UtilisateurApi {

    @PostMapping(value = Constants.API_USER + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save User", notes = "Api to save client in store", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User save successfully."),
            @ApiResponse(code = 400, message = "User not valid.")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = Constants.API_USER + "/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find User By ID", notes = "Api to find user by id in store", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found successfully by ID."),
            @ApiResponse(code = 404, message = "User not found.")
    })
    UtilisateurDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_USER + "/mail={mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find User By Mail", notes = "Api to find user by mail in store", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found successfully by MAIL."),
            @ApiResponse(code = 404, message = "User not found.")
    })
    UtilisateurDto findByEmail(@PathVariable String mail);

    @GetMapping(value = Constants.API_ROOT + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Users", notes = "Api to get all users in store", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero User(s) found successfully."),
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = Constants.API_USER + "/delete/id={id}")
    @ApiOperation(value = "Delete User by ID", notes = "Api to delete user by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
