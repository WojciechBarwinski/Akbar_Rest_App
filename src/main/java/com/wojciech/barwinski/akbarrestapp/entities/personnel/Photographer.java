package com.wojciech.barwinski.akbarrestapp.entities.personnel;

import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Photography;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Photographer {

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

    @OneToMany(mappedBy = "photographer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Photography> photographs;

    @Column(length = 500)
    @Size(max = 500)
    private String photographerNote;
}
