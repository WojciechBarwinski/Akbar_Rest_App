package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePhotoSessionDTO {

    private Long schoolId;
    private Long PhotographId;
    private LocalDate sessionDate;
    private Integer sessionDuration;
    private String note;
}
