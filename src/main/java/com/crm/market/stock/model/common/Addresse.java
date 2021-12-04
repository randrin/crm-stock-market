package com.crm.market.stock.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Addresse {

    private String addresseOne;

    private String addresseTwo;

    private String country;

    private String state;

    private String city;

    private String codePostale;

    private String zipCode;
}
