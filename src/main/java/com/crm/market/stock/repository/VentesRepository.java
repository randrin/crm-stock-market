package com.crm.market.stock.repository;

import com.crm.market.stock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findByCode(String codeVentes);
}
