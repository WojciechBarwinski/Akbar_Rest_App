package com.wojciech.barwinski.akbarrestapp.entities;


import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Photography;
import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Trade;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class School {

    @Id
    private Long rspo;

    @Column(length = 100)
    @Size(max = 100)
    private String type;

    @NotNull
    @Column(length = 100)
    @Size(max = 100)
    private String name;

    private String email;

    private String website;

    @Column(length = 50)
    @Size(max = 50)
    private String publicStatus;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "school")
    private Set<Phone> phones;

    @OneToMany(mappedBy = "key.school")
    private Set<Trade> trades;

    @OneToMany(mappedBy = "key.school")
    private Set<Photography>  photographs;

    @OneToOne
    private AdditionalSchoolInformation additionalSchoolInformation;


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
