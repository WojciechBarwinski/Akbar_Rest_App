package com.wojciech.barwinski.akbarrestapp.staff.entities;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@ToString
@Builder
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

    @OneToMany(mappedBy = "photographer", fetch = EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<PhotoSession> photoSessions;

    @Column(length = 500)
    @Size(max = 500)
    private String note;

    public void addSession(PhotoSession session){
        if (this.photoSessions == null){
            this.photoSessions = new HashSet<>();
        }
        this.photoSessions.add(session);
        session.setPhotographer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photographer that = (Photographer) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, email, note);
    }
}
