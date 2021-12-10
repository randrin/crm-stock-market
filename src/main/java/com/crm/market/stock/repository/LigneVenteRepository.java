package com.crm.market.stock.repository;

import com.crm.market.stock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneVenteRepository extends JpaRepository<Integer, LigneVente> {
}
