package com.crm.market.stock.repository;

import com.crm.market.stock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Integer, Fournisseur> {
}
