package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesmanDeliveryDTO {

    private String school;
    private LocalDate signContractDate;
    private String tradeNote;
}
