package com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TradeBySalesmanDTO {

    private String schoolName;
    private LocalDate signContractDate;
    private String note;
}
