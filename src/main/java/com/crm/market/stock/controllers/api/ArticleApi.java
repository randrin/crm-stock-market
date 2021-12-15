package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.ArticleDto;
import com.crm.market.stock.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Api(Constants.API_ROOT + "Article API")
public interface ArticleApi {

    @PostMapping(value = Constants.API_ROOT + "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Article", notes = "Api to save article in store", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article save successfully."),
            @ApiResponse(code = 400, message = "Article not valid.")
    })
    ArticleDto save(ArticleDto articleDto);

    @GetMapping(value = Constants.API_ROOT + "/article/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Article By ID", notes = "Api to find article by id in store", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article found successfully by ID."),
            @ApiResponse(code = 404, message = "Article not found.")
    })
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_ROOT + "/article/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Article By CODE", notes = "Api to find article by code in store", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article found successfully by CODE."),
            @ApiResponse(code = 404, message = "Article not found.")
    })
    ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = Constants.API_ROOT + "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Articles", notes = "Api to get all articles in store", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All or zero Article(s) found successfully."),
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = Constants.API_ROOT + "/article/delete/id={id}")
    @ApiOperation(value = "Delete Article by ID", notes = "Api to delete article by id in store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article deleted successfully by ID."),
    })
    void deleteById(@PathVariable Integer id);
}
