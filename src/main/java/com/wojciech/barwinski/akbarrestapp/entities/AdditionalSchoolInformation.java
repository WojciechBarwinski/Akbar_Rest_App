package com.wojciech.barwinski.akbarrestapp.entities;


import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdditionalSchoolInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private School school;

    @Embedded
    private Status status;

    @Embedded
    private Notation notation;

    @Embedded
    private Schedule schedule;
}
