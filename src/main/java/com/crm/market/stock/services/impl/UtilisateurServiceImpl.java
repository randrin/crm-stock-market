package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.UtilisateurDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Utilisateur;
import com.crm.market.stock.repository.UtilisateurRepository;
import com.crm.market.stock.services.UtilisateurService;
import com.crm.market.stock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);

        if(!errors.isEmpty()) {
            log.error("Not valid user {}", utilisateurDto);
            throw new InvalidEntityException("Not valid user", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id == null) {
            log.error("Not user found id=", + id + "{}");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(() -> new EntityNotFoundException("Not found user with ID= " +id,
                ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByEmail(String mailUser) {
        if(!StringUtils.hasLength(mailUser)) {
            log.error("Not user found with mail=" + mailUser + "{}");
            return null;
        }
        Optional<Utilisateur> utilisateur  = utilisateurRepository.findByEmail(mailUser);

        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(() -> new EntityNotFoundException("Not found user with MAIL= " +mailUser,
                ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null || !utilisateurRepository.existsById(id)) {
            log.error("Not user found id=", + id + "{}");
            throw new EntityNotFoundException("Not found user with ID= " +id,
                    ErrorCodes.UTILISATEUR_NOT_FOUND);
        }
        utilisateurRepository.deleteById(id);
    }
}
