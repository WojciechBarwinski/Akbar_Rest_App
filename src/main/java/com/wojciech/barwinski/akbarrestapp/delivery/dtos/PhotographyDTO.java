package com.wojciech.barwinski.akbarrestapp.delivery.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PhotographyDTO {

    private String photographer;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private String photographyNote;
}
