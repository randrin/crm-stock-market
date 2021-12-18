package com.crm.market.stock.repository;

import com.crm.market.stock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

    Optional<Fournisseur> findByMail(String mailProvider);
}
