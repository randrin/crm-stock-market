package com.crm.market.stock.handlers;

import com.crm.market.stock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes erroCodes;

    private String message;

    private List<String> errors = new ArrayList<>();
}
