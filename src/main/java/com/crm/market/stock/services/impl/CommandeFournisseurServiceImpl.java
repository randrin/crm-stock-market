package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.CommandeFournisseurDto;
import com.crm.market.stock.dto.LigneCommandeFournisseurDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Article;
import com.crm.market.stock.model.CommandeFournisseur;
import com.crm.market.stock.model.Fournisseur;
import com.crm.market.stock.model.LigneCommandeFournisseur;
import com.crm.market.stock.repository.ArticleRepository;
import com.crm.market.stock.repository.CommandeFournisseurRepository;
import com.crm.market.stock.repository.FournisseurRepository;
import com.crm.market.stock.repository.LigneCommandeFournisseurRepository;
import com.crm.market.stock.services.CommandeFournisseurService;
import com.crm.market.stock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    public CommandeFournisseurServiceImpl(
            CommandeFournisseurRepository commandeFournisseurRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
            FournisseurRepository fournisseurRepository,
            ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if(!errors.isEmpty()) {
            log.error("Not valid order provider {}", commandeFournisseurDto);
            throw new InvalidEntityException("Not valid order provider", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> client = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());

        if(client.isEmpty()) {
            log.error("Provider not found with id=" + commandeFournisseurDto.getFournisseur().getId() + "{}");
            throw new EntityNotFoundException("Provider not found with id=" + commandeFournisseurDto.getFournisseur().getId(), ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articlesErrors = new ArrayList<>();

        if(!commandeFournisseurDto.getLigneCommandeFournisseurs().isEmpty()) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lgnCmdFor -> {
                if(lgnCmdFor.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(lgnCmdFor.getArticle().getId());
                    if(article.isEmpty()) {
                        articlesErrors.add("Article with id=" +lgnCmdFor.getArticle().getId()+ " doesn't exist.");
                    }
                } else {
                    articlesErrors.add("Article with id=" +lgnCmdFor.getArticle().getId()+ " doesn't exist.");
                }
            });
        }

        if(!articlesErrors.isEmpty()) {
            log.warn("Article not found in stock {}");
            throw new InvalidEntityException("Article not found in stock", ErrorCodes.ARTICLE_NOT_FOUND, articlesErrors);
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if(!commandeFournisseurDto.getLigneCommandeFournisseurs().isEmpty()) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lgnCmdFor -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lgnCmdFor);
                ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id == null) {
            log.error("Order client not found with id=", + id + "{}");
            return null;
        }
        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findById(id);

        CommandeFournisseurDto commandeFournisseurDto = CommandeFournisseurDto.fromEntity(commandeFournisseur.get());

        return Optional.of(commandeFournisseurDto).orElseThrow(() -> new EntityNotFoundException("Not found order provider with ID= " +id,
                ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Order provider not found with code=" + code + "{}");
            return null;
        }
        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findByCode(code);

        CommandeFournisseurDto commandeFournisseurDto = CommandeFournisseurDto.fromEntity(commandeFournisseur.get());

        return Optional.of(commandeFournisseurDto).orElseThrow(() -> new EntityNotFoundException("Not found order provider with CODE= " +code,
                ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null || !commandeFournisseurRepository.existsById(id)) {
            log.error("Order provider not found with id=", + id + "{}");
            throw new EntityNotFoundException("Not found order provider with ID= " +id,
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
