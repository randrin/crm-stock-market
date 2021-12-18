package com.crm.market.stock.repository;

import com.crm.market.stock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

    Optional<Entreprise> findByEmail(String mailEntreprise);
}
