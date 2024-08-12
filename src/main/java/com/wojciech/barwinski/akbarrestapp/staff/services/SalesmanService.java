package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.delivery.repositories.TradeRepository;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.UpdateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.mappers.StaffMapper;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.SalesmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
class SalesmanService {

    private final SalesmanRepository salesmanRepository;
    private final StaffMapper mappers;
    private final TradeRepository tradeRepository;

    public SalesmanDTO getSalesmanById(Long id) {
        Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));
        return mappers.mapSalesmanToDTO(salesman);
    }

    public SalesmanDTO getSalesmanByLastname(String lastName) {
        Salesman salesman = salesmanRepository.findByLastName(lastName)
                .orElseThrow(() -> new StaffNotFoundException(lastName));
        return mappers.mapSalesmanToDTO(salesman);
    }

    public SalesmanDTO createSalesman(CreateStaffDTO createStaffDTO) {
        Salesman salesman = mappers.mapNewStaffDTOToSalesman(createStaffDTO);

        Salesman createdSalesman = salesmanRepository.save(salesman);

        return mappers.mapSalesmanToDTO(createdSalesman);
    }

    public void deleteSalesmanById(Long id) {
        Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));

        removeSalesmanFromTrades(salesman);

        salesmanRepository.deleteById(id);
    }

    public void deleteSalesmanByLastname(String lastName) {
        Salesman salesman = salesmanRepository.findByLastName(lastName)
                .orElseThrow(() -> new StaffNotFoundException(lastName));

        removeSalesmanFromTrades(salesman);

        salesmanRepository.deleteByLastName(lastName);
    }

    public SalesmanDTO updateSalesman(UpdateStaffDTO updateStaffDTO) {
        Salesman salesman = mappers.mapUpdateStaffDTOToSalesman(updateStaffDTO);

        Salesman updatedSalesman = salesmanRepository.save(salesman);

        return mappers.mapSalesmanToDTO(updatedSalesman);
    }

    void removeSalesmanFromTrades(Salesman salesman){

        Set<Trade> trades = salesman.getTrades();

        for (Trade trade : trades) {
            trade.setSalesman(null);
        }

        tradeRepository.saveAll(trades);

    }
}
