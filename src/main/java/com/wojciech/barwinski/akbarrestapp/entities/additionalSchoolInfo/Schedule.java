package com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Schedule {

    private LocalDate contact;

    private LocalDate signContractDate;

    private LocalDate photographingDate;

    private Integer photographyDaysCount;

    private LocalDate payoff;

    private LocalDate other;

    @Column(length = 500)
    @Size(max = 500)
    private String scheduleNote;

}
