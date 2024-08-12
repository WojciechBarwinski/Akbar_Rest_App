package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePhotoSessionDTO {

    private Long id;
    private Long schoolId;
    private Long PhotographId;
    private LocalDate sessionDate;
    private Integer sessionDuration;
    private String note;

}
