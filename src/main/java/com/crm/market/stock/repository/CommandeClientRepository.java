package com.crm.market.stock.repository;

import com.crm.market.stock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeClientRepository extends JpaRepository<Integer, CommandeClient> {
}
