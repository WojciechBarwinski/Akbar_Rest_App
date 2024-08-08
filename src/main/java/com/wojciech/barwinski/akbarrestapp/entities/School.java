package com.wojciech.barwinski.akbarrestapp.entities;


import com.wojciech.barwinski.akbarrestapp.delivery.entities.Photography;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Audited
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
    private String status;

    @Embedded()
    private Address address;

    @OneToMany(mappedBy = "school", fetch = LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Phone> phones;

    @OneToMany(mappedBy = "school", fetch = EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotAudited
    private Set<Trade> trades;

    @OneToMany(mappedBy = "school", fetch = EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotAudited
    private Set<Photography> photographs;

    @OneToOne(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_info_id", foreignKey = @ForeignKey(name = "FK_SCHOOL_INFO"))
    private AdditionalSchoolInformation additionalSchoolInformation;

    public School(Long rspo, String type, String name, String email, String website, String publicStatus) {
        this.rspo = rspo;
        this.type = type;
        this.name = name;
        this.email = email;
        this.website = website;
        this.status = publicStatus;
    }

    public School(Long rspo, String type, String name, String email, String website, String publicStatus, Address address) {
        this.rspo = rspo;
        this.type = type;
        this.name = name;
        this.email = email;
        this.website = website;
        this.status = publicStatus;
        this.address = address;
    }

    public void setPhones(List<Phone> phones) {
        if (this.phones == null) {
            this.phones = new ArrayList<>();
        }
        for (Phone p : phones) {
            this.phones.add(p);
            p.setSchool(this);
        }
    }

    public void addPhone(Phone phone) {
        if (this.phones == null) {
            this.phones = new ArrayList<>();
        }
        phones.add(phone);
        phone.setSchool(this);
    }

    public void setAdditionalSchoolInformation(AdditionalSchoolInformation additionalSchoolInformation) {
        this.additionalSchoolInformation = additionalSchoolInformation;
        additionalSchoolInformation.setSchool(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        School school = (School) o;
        return getRspo() != null && Objects.equals(getRspo(), school.getRspo());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
