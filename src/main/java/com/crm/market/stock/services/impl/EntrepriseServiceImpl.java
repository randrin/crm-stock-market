package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.EntrepriseDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Entreprise;
import com.crm.market.stock.repository.EntrepriseRepository;
import com.crm.market.stock.services.EntrepriseService;
import com.crm.market.stock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if(!errors.isEmpty()) {
            log.error("Not valid business {}", entrepriseDto);
            throw new InvalidEntityException("Not valid business", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if(id == null) {
            log.error("Not business found id=", + id + "{}");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(entrepriseDto).orElseThrow(() -> new EntityNotFoundException("Not found business with ID= " +id,
                ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public EntrepriseDto findByEmail(String mailEntreprise) {
        if(!StringUtils.hasLength(mailEntreprise)) {
            log.error("Not business found with mail=" + mailEntreprise + "{}");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findByEmail(mailEntreprise);

        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(entrepriseDto).orElseThrow(() -> new EntityNotFoundException("Not found business with MAIL= " +mailEntreprise,
                ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            log.error("Not business found id=", + id + "{}");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
