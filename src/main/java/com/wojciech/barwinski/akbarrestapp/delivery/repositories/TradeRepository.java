package com.wojciech.barwinski.akbarrestapp.delivery.repositories;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    Set<Trade> findBySalesmanId(Long id);

    Set<Trade> findBySchoolRspo(Long rspo);
}
