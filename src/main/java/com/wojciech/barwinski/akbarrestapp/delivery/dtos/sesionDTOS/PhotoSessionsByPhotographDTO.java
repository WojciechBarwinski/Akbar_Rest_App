package com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class PhotoSessionsByPhotographDTO {

    private Long photographId;
    private String photographName;
    private List<SessionByPhotographDTO> photoSessionDTOS;
}
