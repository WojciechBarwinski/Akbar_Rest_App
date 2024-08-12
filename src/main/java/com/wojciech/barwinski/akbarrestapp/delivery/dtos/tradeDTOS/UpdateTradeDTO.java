package com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTradeDTO {

    private Long id;
    private Long schoolId;
    private Long salesmanId;
    private LocalDate signContractDate;
    private String note;

}
