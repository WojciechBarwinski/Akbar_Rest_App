package com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FullTradesBySchoolDTO {

    private Long schoolId;
    private String SchoolName;
    private List<TradeBySchoolDTO> tradeBySchoolDTOS;
}
