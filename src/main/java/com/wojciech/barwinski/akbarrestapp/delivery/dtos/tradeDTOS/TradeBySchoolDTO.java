package com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TradeBySchoolDTO {

    private String salesmanName;
    private LocalDate signContractDate;
    private String note;

}
