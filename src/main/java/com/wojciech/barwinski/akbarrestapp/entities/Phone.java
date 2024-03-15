package com.wojciech.barwinski.akbarrestapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Size(max = 12)
    private String number;

    private boolean isMain;

    private String owner;

    private String phoneNote;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo", foreignKey = @ForeignKey(name = "FK_PHONE_SCHOOL"))
    private School school;

    public void setSchool(School school) {
        this.school = school;
    }

}
