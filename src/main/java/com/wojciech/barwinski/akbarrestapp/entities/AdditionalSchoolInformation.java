package com.wojciech.barwinski.akbarrestapp.entities;


import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdditionalSchoolInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "additionalSchoolInformation")
    private School school;

    @Embedded
    private Status status;

    @Embedded
    private Notation notation;

    @Embedded
    private Schedule schedule;

    public AdditionalSchoolInformation(Status status, Notation notation, Schedule schedule) {
        this.status = status;
        this.notation = notation;
        this.schedule = schedule;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
