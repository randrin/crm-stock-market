package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.CommandeClientDto;
import com.crm.market.stock.dto.LigneCommandeClientDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Article;
import com.crm.market.stock.model.Client;
import com.crm.market.stock.model.CommandeClient;
import com.crm.market.stock.model.LigneCommandeClient;
import com.crm.market.stock.repository.ArticleRepository;
import com.crm.market.stock.repository.ClientRepository;
import com.crm.market.stock.repository.CommandeClientRepository;
import com.crm.market.stock.repository.LigneCommandeClientRepository;
import com.crm.market.stock.services.CommandeClientService;
import com.crm.market.stock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(
            CommandeClientRepository commandeClientRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository,
            ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if(!errors.isEmpty()) {
            log.error("Not valid order client {}", commandeClientDto);
            throw new InvalidEntityException("Not valid order client", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());

        if(client.isEmpty()) {
            log.error("Client not found with id=" + commandeClientDto.getClient().getId() + "{}");
            throw new EntityNotFoundException("Client not found with id=" + commandeClientDto.getClient().getId(), ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articlesErrors = new ArrayList<>();

        if(!commandeClientDto.getLigneCommandeClients().isEmpty()) {
            commandeClientDto.getLigneCommandeClients().forEach(lgnCmdClt -> {
                if(lgnCmdClt.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(lgnCmdClt.getArticle().getId());
                    if(article.isEmpty()) {
                        articlesErrors.add("Article with id=" +lgnCmdClt.getArticle().getId()+ " doesn't exist.");
                    }
                } else {
                    articlesErrors.add("Article with id=" +lgnCmdClt.getArticle().getId()+ " doesn't exist.");
                }
            });
        }

        if(!articlesErrors.isEmpty()) {
            log.warn("Article not found in stock {}");
            throw new InvalidEntityException("Article not found in stock", ErrorCodes.ARTICLE_NOT_FOUND, articlesErrors);
        }

        CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if(!commandeClientDto.getLigneCommandeClients().isEmpty()) {
            commandeClientDto.getLigneCommandeClients().forEach(lgnCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lgnCmdClt);
                ligneCommandeClient.setCommandeClient(commandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if(id == null) {
            log.error("Order client not found with id=", + id + "{}");
            return null;
        }
        Optional<CommandeClient> commandeClient = commandeClientRepository.findById(id);

        CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient.get());

        return Optional.of(commandeClientDto).orElseThrow(() -> new EntityNotFoundException("Not found order client with ID= " +id,
                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Order client not found with code=" + code + "{}");
            return null;
        }
        Optional<CommandeClient> commandeClient = commandeClientRepository.findByCode(code);

        CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient.get());

        return Optional.of(commandeClientDto).orElseThrow(() -> new EntityNotFoundException("Not found order client with CODE= " +code,
                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null || !commandeClientRepository.existsById(id)) {
            log.error("Order client not found with id=", + id + "{}");
            throw new EntityNotFoundException("Order client not found with id=" + id, ErrorCodes.CLIENT_NOT_FOUND);
        }
        commandeClientRepository.deleteById(id);
    }
}
