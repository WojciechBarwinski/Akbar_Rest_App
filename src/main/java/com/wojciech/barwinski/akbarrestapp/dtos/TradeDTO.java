package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Data
@Builder
public class TradeDTO {

    private String salesman;
    private LocalDate signContractDate;
    private String tradeNote;
}
