package com.wojciech.barwinski.akbarrestapp.delivery.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DeliverableDTO {

    private List<PhotoSessionDTO> photoSessionDTOS = new ArrayList<>();
    private List<TradeDTO> tradeDTOS = new ArrayList<>();

}
