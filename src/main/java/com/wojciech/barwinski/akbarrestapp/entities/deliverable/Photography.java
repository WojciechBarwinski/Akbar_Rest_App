package com.wojciech.barwinski.akbarrestapp.entities.deliverable;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.personnel.Photographer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Photography {

    @Id
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo")
    private School school;

    private LocalDate photographingDate;

    private Integer photographyDaysCount;

    @Column(length = 500)
    @Size(max = 500)
    private String tradeNote;
}
