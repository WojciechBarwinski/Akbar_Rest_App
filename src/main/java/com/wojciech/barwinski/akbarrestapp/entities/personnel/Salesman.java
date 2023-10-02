package com.wojciech.barwinski.akbarrestapp.entities.personnel;


import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Trade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
/*
@Getter
@Setter*/
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Size(max = 20)
    private String firstName;

    @Column(length = 20)
    @Size(max = 20)
    private String lastName;

    @Column(length = 20)
    @Size(max = 20)
    private String phone;

    private String email;

    @OneToMany(mappedBy = "salesman", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Trade> trades;

    @Column(length = 500)
    @Size(max = 500)
    private String salesmanNote;

}
