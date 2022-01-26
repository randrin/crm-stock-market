package com.crm.market.stock.services.impl;

import com.crm.market.stock.dto.EntrepriseDto;
import com.crm.market.stock.dto.RolesDto;
import com.crm.market.stock.dto.UtilisateurDto;
import com.crm.market.stock.exception.EntityNotFoundException;
import com.crm.market.stock.exception.ErrorCodes;
import com.crm.market.stock.exception.InvalidEntityException;
import com.crm.market.stock.model.Entreprise;
import com.crm.market.stock.repository.EntrepriseRepository;
import com.crm.market.stock.repository.RolesRepository;
import com.crm.market.stock.services.EntrepriseService;
import com.crm.market.stock.services.UtilisateurService;
import com.crm.market.stock.utils.Constants;
import com.crm.market.stock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository,
                                 UtilisateurService utilisateurService, RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if(!errors.isEmpty()) {
            log.error("Not valid business {}", entrepriseDto);
            throw new InvalidEntityException("Not valid business", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        EntrepriseDto entrepriseSaved =  EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
        UtilisateurDto utilisateurDto = fromEntreprise(entrepriseSaved);
        UtilisateurDto userSaved = utilisateurService.save(utilisateurDto);

        RolesDto rolesDto = RolesDto.builder()
                .roleName(Constants.STOCK_ROLE_ADMIN)
                .utilisateur(userSaved)
                .build();
        rolesRepository.save(RolesDto.toEntity(rolesDto));
        return entrepriseSaved;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto entrepriseSaved) {
        return UtilisateurDto.builder()
                .addresse(entrepriseSaved.getAddresse())
                .nom(entrepriseSaved.getNom())
                .prenom(entrepriseSaved.getCodeFiscale())
                .email(entrepriseSaved.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(entrepriseSaved)
                .dateDeNaissance(Instant.now().toString())
                .photo(entrepriseSaved.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "123456789";
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
        if(id == null || !entrepriseRepository.existsById(id)) {
            log.error("Not business found id=", + id + "{}");
            throw new EntityNotFoundException("Not found business with ID= " +id,
                    ErrorCodes.ENTREPRISE_NOT_FOUND);
        }
        entrepriseRepository.deleteById(id);
    }
}
