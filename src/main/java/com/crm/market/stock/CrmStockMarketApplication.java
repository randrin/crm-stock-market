package com.crm.market.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrmStockMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmStockMarketApplication.class, args);
	}

}
