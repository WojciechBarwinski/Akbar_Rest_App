package com.wojciech.barwinski.akbarrestapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

/*@Getter
@Setter*/
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Size(max = 20)
    private String number;

    private String owner;

    private String phoneNote;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo", foreignKey = @ForeignKey(name = "FK_PHONE_SCHOOL"))
    private School school;

    public Phone(String number, String owner, String phoneNote) {
        this.number = number;
        this.owner = owner;
        this.phoneNote = phoneNote;
    }

    public Long getId() {
        return id;
    }

    //TODO jesli szkoła juz jest zapisana oraz sprawdzenie czy school nie jest nullem
    public void setSchool(School school) {
        this.school = school;
    }
}
