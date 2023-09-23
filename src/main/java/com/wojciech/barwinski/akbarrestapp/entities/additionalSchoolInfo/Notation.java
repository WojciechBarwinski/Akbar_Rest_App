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
public class Notation {

    @Column(length = 500)
    @Size(max = 500)
    private String notation1;

    @Column(length = 500)
    @Size(max = 500)
    private String notation2;

    @Column(length = 500)
    @Size(max = 500)
    private String notation3;
}
