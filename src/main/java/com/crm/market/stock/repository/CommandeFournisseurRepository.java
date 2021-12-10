package com.crm.market.stock.repository;

import com.crm.market.stock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<Integer, CommandeFournisseur> {
}