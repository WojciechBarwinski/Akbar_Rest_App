package com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Schedule {

    private LocalDate contact;

    private LocalDate photographing;

    private Integer photographingDay;

    private LocalDate payoff;

    private LocalDate other;

    @Column(length = 500)
    @Size(max = 500)
    private String scheduleNote;
}
