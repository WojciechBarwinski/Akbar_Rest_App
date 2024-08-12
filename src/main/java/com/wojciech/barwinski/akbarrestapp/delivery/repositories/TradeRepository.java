package com.wojciech.barwinski.akbarrestapp.delivery.repositories;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
