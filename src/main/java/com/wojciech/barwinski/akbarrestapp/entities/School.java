package com.wojciech.barwinski.akbarrestapp.entities;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class School {

    @Id
    private Long rspo;

    private String type;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    private String email;

    private String website;

    private String publicStatus;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School school)) return false;
        return rspo.equals(school.rspo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rspo);
    }
}
