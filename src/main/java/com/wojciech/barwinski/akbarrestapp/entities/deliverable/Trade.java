package com.wojciech.barwinski.akbarrestapp.entities.deliverable;


import com.wojciech.barwinski.akbarrestapp.entities.deliverable.TradeKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Trade {

    @Id
    private TradeKey key;

    private LocalDate signContractDate;

    @Column(length = 500)
    @Size(max = 500)
    private String tradeNote;
}
