package com.wojciech.barwinski.akbarrestapp.entities.deliverable;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.personnel.Salesman;
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
public class Trade {

    @Id
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
    private String tradeNote;
}
