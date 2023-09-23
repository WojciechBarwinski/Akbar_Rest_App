package com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Status {

   private boolean isOurs;

    private boolean isContracted;

    private boolean isPhoto;

    private boolean isSettle;

    @Column(length = 500)
    @Size(max = 500)
    private String statusNote;

}
