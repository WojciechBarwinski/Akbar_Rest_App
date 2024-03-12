package com.wojciech.barwinski.akbarrestapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
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

    /*protected Phone() {
    }

    private Phone(PhoneBuilder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.owner = builder.owner;
        this.phoneNote = builder.phoneNote;
        this.school = builder.school;
    }*/

    public void setSchool(School school) {
        this.school = school;
    }

/*    public static class PhoneBuilder {

        private Long id;
        private String number;
        private String owner;
        private String phoneNote;
        private School school;


        public PhoneBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PhoneBuilder number(String number) {
            this.number = number;
            return this;
        }

        public PhoneBuilder owner(String owner) {
            this.owner = owner;
            return this;
        }

        public PhoneBuilder phoneNote(String phoneNote) {
            this.phoneNote = phoneNote;
            return this;
        }

        public PhoneBuilder school(School school) {
            this.school = school;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }

    }*/
}
