package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.LigneVenteDto;
import com.crm.market.stock.dto.VentesDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Article;
import com.crm.market.stock.model.LigneVente;
import com.crm.market.stock.model.Ventes;
import com.crm.market.stock.repository.ArticleRepository;
import com.crm.market.stock.repository.LigneVenteRepository;
import com.crm.market.stock.repository.VentesRepository;
import com.crm.market.stock.services.VentesService;
import com.crm.market.stock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    public VentesServiceImpl(
            ArticleRepository articleRepository,
            VentesRepository ventesRepository,
            LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {

        List<String> errors = VentesValidator.validate(ventesDto);

        if(!errors.isEmpty()) {
            log.error("Not valid sales {}", ventesDto);
            throw new InvalidEntityException("Not valid sales", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articlesErrors = new ArrayList<>();

        if(!ventesDto.getLigneVentes().isEmpty()) {
            ventesDto.getLigneVentes().forEach(lgnVte -> {
                if(lgnVte.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(lgnVte.getArticle().getId());
                    if(article.isEmpty()) {
                        articlesErrors.add("Article with id=" +lgnVte.getArticle().getId()+ " doesn't exist.");
                    }
                } else {
                    articlesErrors.add("Article with id=" +lgnVte.getArticle().getId()+ " doesn't exist.");
                }
            });
        }

        if(!articlesErrors.isEmpty()) {
            log.warn("Article(s) not found in stock {}", errors);
            throw new InvalidEntityException("Article(s) not found in stock", ErrorCodes.ARTICLE_NOT_FOUND, articlesErrors);
        }

        Ventes ventes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        if(!ventesDto.getLigneVentes().isEmpty()) {
            ventesDto.getLigneVentes().forEach(lgnVte -> {
                LigneVente ligneVente = LigneVenteDto.toEntity(lgnVte);
                ligneVente.setVente(ventes);
                ligneVenteRepository.save(ligneVente);
            });
        }

        return VentesDto.fromEntity(ventes);
    }

    @Override
    public VentesDto findById(Integer id) {

        if(id == null) {
            log.error("Sales not found with id=", + id + "{}");
            return null;
        }
        Optional<Ventes> ventes = ventesRepository.findById(id);

        VentesDto ventesDto = VentesDto.fromEntity(ventes.get());

        return Optional.of(ventesDto).orElseThrow(() -> new EntityNotFoundException("Not found sales with ID= " +id,
                ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Sales not found with code=" + code + "{}");
            return null;
        }
        Optional<Ventes> ventes = ventesRepository.findByCode(code);

        VentesDto ventesDto = VentesDto.fromEntity(ventes.get());

        return Optional.of(ventesDto).orElseThrow(() -> new EntityNotFoundException("Not found sales with CODE= " +code,
                ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            log.error("Sales not found with id=", + id + "{}");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
