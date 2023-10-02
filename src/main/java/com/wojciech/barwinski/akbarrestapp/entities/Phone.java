package com.wojciech.barwinski.akbarrestapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Size(max = 9)
    private String number;

    private String owner;

    private String phoneNote;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo", foreignKey = @ForeignKey(name = "FK_PHONE_SCHOOL"))
    private School school;

    protected Phone() {
    }

    private Phone(PhoneBuilder builder){
        if (builder.id != null){
            this.id = builder.id;
        }
        this.number = builder.number;
        this.owner = builder.owner;
        this.phoneNote = builder.phoneNote;

    }

    public Long getId() {
        return id;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public static class PhoneBuilder{

        private Long id;
        private String number;
        private String owner;
        private String phoneNote;


        public PhoneBuilder id(Long id){
            this.id = id;
            return this;
        }

        public PhoneBuilder number(String number){
            if(!number.matches("\\d{9}")) {
                throw new IllegalArgumentException("Number must be 9 digits");
            }
            this.number = number;
            return this;
        }

        public PhoneBuilder owner(String owner){
            this.owner = owner;
            return this;
        }

        public PhoneBuilder phoneNote(String phoneNote){
            this.phoneNote = phoneNote;
            return this;
        }

        public Phone build(){
            return new Phone(this);
        }

    }
}
