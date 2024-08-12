package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class SessionBySchoolDTO {

    private String photographer;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private String photographyNote;
}
