package com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FullTradesBySalesmanDTO {

    private Long salesmanId;
    private String salesmanName;
    private List<TradeBySalesmanDTO> tradeBySalesmanDTOS;
}
