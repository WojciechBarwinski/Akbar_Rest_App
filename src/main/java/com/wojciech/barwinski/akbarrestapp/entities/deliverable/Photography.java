package com.wojciech.barwinski.akbarrestapp.entities.deliverable;


import com.wojciech.barwinski.akbarrestapp.entities.deliverable.PhotographyKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Photography {

    @Id
    private PhotographyKey key;

    private LocalDate photographingDate;

    private Integer photographyDaysCount;

    @Column(length = 500)
    @Size(max = 500)
    private String tradeNote;
}
