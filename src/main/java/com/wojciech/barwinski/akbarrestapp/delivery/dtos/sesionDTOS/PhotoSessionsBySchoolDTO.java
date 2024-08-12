package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PhotoSessionsBySchoolDTO {

    private Long schoolId;
    private String SchoolName;
    private List<SessionBySchoolDTO> photoSessionDTOS;
}
