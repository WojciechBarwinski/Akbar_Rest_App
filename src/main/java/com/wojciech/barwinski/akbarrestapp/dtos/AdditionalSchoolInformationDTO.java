package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdditionalSchoolInformationDTO {

    //Status
    private Boolean isOurs;
    private Boolean isContracted;
    private Boolean isPhoto;
    private Boolean isSettle;
    private String statusNote;
    //Schedule
    private LocalDate contact;
    private LocalDate signContractDate;
    private LocalDate photographingDate;
    private Integer photographyDaysCount;
    private LocalDate payoff;
    private LocalDate other;
    private String scheduleNote;
    //Notation
    private String notation1;
    private String notation2;
    private String notation3;

}
