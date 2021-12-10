package com.crm.market.stock.repository;

import com.crm.market.stock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Integer, Article> {
}
