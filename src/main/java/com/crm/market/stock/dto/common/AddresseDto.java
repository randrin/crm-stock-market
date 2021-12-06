package com.crm.market.stock.dto.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddresseDto {

    private String addresseOne;

    private String addresseTwo;

    private String country;

    private String state;

    private String city;

    private String codePostale;

    private String zipCode;
}
