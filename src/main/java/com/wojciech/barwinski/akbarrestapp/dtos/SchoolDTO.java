package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {

    private Long rspo;
    private String name;
    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private List<PhoneDTO> phones;
    private AdditionalSchoolInformationDTO additionalSchoolInformationDTO;

}
