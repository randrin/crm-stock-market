package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.FournisseurDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Fournisseur;
import com.crm.market.stock.repository.FournisseurRepository;
import com.crm.market.stock.services.FournisseurService;
import com.crm.market.stock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);

        if(!errors.isEmpty()) {
            log.error("Not valid provider {}", fournisseurDto);
            throw new InvalidEntityException("Not valid provider", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null) {
            log.error("Not provider found id=", + id + "{}");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(fournisseurDto).orElseThrow(() -> new EntityNotFoundException("Not found provider with ID= " +id,
                ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public FournisseurDto findByMail(String mailProvider) {
        if(!StringUtils.hasLength(mailProvider)) {
            log.error("Not provider found with mail=" + mailProvider + "{}");
            return null;
        }
        Optional<Fournisseur> fournisseur  = fournisseurRepository.findByMail(mailProvider);

        FournisseurDto fournisseurDto  = FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(fournisseurDto).orElseThrow(() -> new EntityNotFoundException("Not found provider with MAIL= " +mailProvider,
                ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            log.error("Not provider found id=", + id + "{}");
            return;
        }
        fournisseurRepository.deleteById(id);
    }
}
