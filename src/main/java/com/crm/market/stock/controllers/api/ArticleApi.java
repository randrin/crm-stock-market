package com.crm.market.stock.controllers.api;

import com.crm.market.stock.dto.ArticleDto;
import com.crm.market.stock.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ArticleApi {

    @PostMapping(value = Constants.API_ROOT + "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(ArticleDto articleDto);

    @GetMapping(value = Constants.API_ROOT + "/article/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping(value = Constants.API_ROOT + "/article/code={code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = Constants.API_ROOT + "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = Constants.API_ROOT + "/article/delete/id={id}")
    void deleteById(@PathVariable Integer id);
}
