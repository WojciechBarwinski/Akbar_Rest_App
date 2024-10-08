package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhotoSessionDTO {

    private String schoolName;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private String note;
}
