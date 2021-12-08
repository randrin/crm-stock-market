package com.crm.market.stock.dto.common;

import com.crm.market.stock.model.common.Addresse;
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

    public static AddresseDto fromEntity(Addresse addresse) {
        if (addresse == null) {
            return null;
            // TODO throws on ecxeption
        }
        return AddresseDto.builder()
                .addresseOne(addresse.getAddresseOne())
                .addresseTwo(addresse.getAddresseTwo())
                .country(addresse.getCountry())
                .state(addresse.getState())
                .city(addresse.getCity())
                .codePostale(addresse.getCodePostale())
                .zipCode(addresse.getZipCode())
                .build();
    }

    public static Addresse toEntity(AddresseDto addresseDto) {
        if(addresseDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Addresse addresse = new Addresse();
        addresse.setAddresseOne(addresseDto.getAddresseOne());
        addresse.setAddresseTwo(addresseDto.getAddresseTwo());
        addresse.setCountry(addresseDto.getCountry());
        addresse.setState(addresseDto.getState());
        addresse.setCity(addresseDto.getCity());
        addresse.setCodePostale(addresseDto.getCodePostale());
        addresse.setZipCode(addresseDto.getZipCode());

        return addresse;
    }
}
