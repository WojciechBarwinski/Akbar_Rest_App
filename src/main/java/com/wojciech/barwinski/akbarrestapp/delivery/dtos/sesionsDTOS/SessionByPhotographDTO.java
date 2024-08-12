package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class SessionByPhotographDTO {

    private String schoolName;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private String photographyNote;
}
