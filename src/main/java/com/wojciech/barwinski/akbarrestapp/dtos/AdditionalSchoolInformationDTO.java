package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.CorrectString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdditionalSchoolInformationDTO {

    @NotNull
    private Long id;

    //Status
    private Boolean isOurs;
    private Boolean isContracted;
    private Boolean isPhoto;
    private Boolean isSettle;
    @CorrectString()
    private String statusNote;

    //Schedule
    private LocalDate contact;
    private LocalDate signContractDate;
    private LocalDate photographingDate;

    @PositiveOrZero
    private Integer photographyDaysCount;
    private LocalDate payoff;
    private LocalDate other;

    @CorrectString()
    private String scheduleNote;

    //Notation
    @Size(max = 500)
    private String notation1;
    @Size(max = 500)
    private String notation2;
    @Size(max = 500)
    private String notation3;

}
