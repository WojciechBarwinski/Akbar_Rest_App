package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolToViewDTO {

    private Long rspo;
    private String name;
    private String type;
    private String email;
    private String website;
    private String status;
    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String zipCode;
    private String addressNote;
    private List<PhoneToViewDTO> phones;
    private AdditionalSchoolInformationDTO additionalSchoolInformationDTO;
    private DeliverableDTO deliverableDTO;

}
