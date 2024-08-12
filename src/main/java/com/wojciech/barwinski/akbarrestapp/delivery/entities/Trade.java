package com.wojciech.barwinski.akbarrestapp.delivery.entities;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "salesman_id", foreignKey = @ForeignKey(name = "FK_TRADE_SALESMAN"))
    private Salesman salesman;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_rspo", foreignKey = @ForeignKey(name = "FK_TRADE_SCHOOL"))
    private School school;

    private LocalDate signContractDate;

    @Column(length = 500)
    @Size(max = 500)
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(id, trade.id) && Objects.equals(signContractDate, trade.signContractDate) && Objects.equals(note, trade.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signContractDate, note);
    }
}
