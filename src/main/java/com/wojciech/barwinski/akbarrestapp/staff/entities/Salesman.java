package com.wojciech.barwinski.akbarrestapp.staff.entities;


import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Data
@Builder
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

    @OneToMany(mappedBy = "salesman", fetch = EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Trade> trades;

    @Column(length = 500)
    @Size(max = 500)
    private String note;

    public void addTrade(Trade trades){
        if (this.trades == null){
            this.trades = new HashSet<>();
        }
        this.trades.add(trades);
        trades.setSalesman(this);
    }
}
