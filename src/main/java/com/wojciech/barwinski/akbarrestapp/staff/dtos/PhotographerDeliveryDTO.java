package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PhotographerDeliveryDTO {

    private String School;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private String photographyNote;
    //private List<PhotographerDeliveryDTO> photography;
}
