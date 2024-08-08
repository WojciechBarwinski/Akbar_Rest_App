package com.wojciech.barwinski.akbarrestapp.delivery.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TradeDTO {

    private String salesman;
    private LocalDate signContractDate;
    private String tradeNote;
}
