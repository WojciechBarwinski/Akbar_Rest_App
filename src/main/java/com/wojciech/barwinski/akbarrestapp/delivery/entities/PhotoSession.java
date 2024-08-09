package com.wojciech.barwinski.akbarrestapp.delivery.entities;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PhotoSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "photographer_id", foreignKey = @ForeignKey(name = "FK_PHOTOSESSION_PHOTOGRAPHER"))
    @ToString.Exclude
    private Photographer photographer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo", foreignKey = @ForeignKey(name = "FK_PHOTOSESSION_SCHOOL"))
    @ToString.Exclude
    private School school;

    private LocalDate photographingDate;

    private Integer photographyDaysCount;

    @Column(length = 500)
    @Size(max = 500)
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoSession that = (PhotoSession) o;
        return Objects.equals(id, that.id) && Objects.equals(photographingDate, that.photographingDate) && Objects.equals(photographyDaysCount, that.photographyDaysCount) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photographingDate, photographyDaysCount, note);
    }
}
