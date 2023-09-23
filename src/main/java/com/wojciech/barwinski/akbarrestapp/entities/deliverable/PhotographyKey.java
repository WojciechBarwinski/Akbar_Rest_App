package com.wojciech.barwinski.akbarrestapp.entities.deliverable;

import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.personnel.Photographer;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
@Getter
public class PhotographyKey implements Serializable {

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Photographer photographer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private School school;
}
