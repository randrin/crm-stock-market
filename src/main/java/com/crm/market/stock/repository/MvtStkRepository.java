package com.crm.market.stock.repository;

import com.crm.market.stock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MvtStkRepository extends JpaRepository<Integer, MvtStk> {
}
