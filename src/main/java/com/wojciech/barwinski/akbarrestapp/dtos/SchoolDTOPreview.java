package com.wojciech.barwinski.akbarrestapp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTOPreview {

    @Schema(name = "School ID/RSPO", example = "101")
    private Long rspo;
    private String name;
    private String voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String phone;
}
