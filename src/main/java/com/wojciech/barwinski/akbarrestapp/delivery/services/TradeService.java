package com.wojciech.barwinski.akbarrestapp.delivery.services;

import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.CreateTradeDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.UpdateTradeDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import com.wojciech.barwinski.akbarrestapp.delivery.exceptions.NoneDeliveryException;
import com.wojciech.barwinski.akbarrestapp.delivery.mappers.DeliveryMapper;
import com.wojciech.barwinski.akbarrestapp.delivery.repositories.TradeRepository;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.exception.SchoolNotFoundException;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.SalesmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final SchoolRepository schoolRepository;
    private final SalesmanRepository salesmanRepository;
    private final DeliveryMapper mapper;

    public FullTradesBySchoolDTO getTradesBySchoolId(Long id) {
        Set<Trade> tradesBySchool = tradeRepository.findBySchoolRspo(id);

        if (tradesBySchool.isEmpty()) {
            throw new NoneDeliveryException("There is none trades by school with id: " + id);
        }
        return mapper.mapTradesToFullTradeBySchool(tradesBySchool);
    }

    public FullTradesBySalesmanDTO getTradesBySalesmanId(Long id) {
        Set<Trade> tradesBySalesman = tradeRepository.findBySalesmanId(id);

        if (tradesBySalesman.isEmpty()) {
            throw new NoneDeliveryException("There is none trades by salesman with id: " + id);
        }
        return mapper.mapTradesToFullTradeBySalesman(tradesBySalesman);
    }

    public FullTradesBySchoolDTO createTrade(CreateTradeDTO createTradeDTO) {
        School school = schoolRepository.findByRspo(createTradeDTO.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + createTradeDTO.getSchoolId()));

        Salesman salesman = salesmanRepository.findById(createTradeDTO.getSalesmanId())
                .orElseThrow(() -> new StaffNotFoundException(createTradeDTO.getSalesmanId().toString()));

        Trade trade = Trade.builder()
                .salesman(salesman)
                .school(school)
                .signContractDate(createTradeDTO.getSignContractDate())
                .note(createTradeDTO.getNote())
                .build();

        tradeRepository.save(trade);
        Set<Trade> tradeBySchool = tradeRepository.findBySchoolRspo(createTradeDTO.getSchoolId());

        return mapper.mapTradesToFullTradeBySchool(tradeBySchool);
    }

    public FullTradesBySchoolDTO updateTrade(UpdateTradeDTO updateTradeDTO) {
        School school = schoolRepository.findByRspo(updateTradeDTO.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + updateTradeDTO.getSchoolId()));

        Salesman salesman = salesmanRepository.findById(updateTradeDTO.getSalesmanId())
                .orElseThrow(() -> new StaffNotFoundException(updateTradeDTO.getSalesmanId().toString()));

        Trade trade = Trade.builder()
                .id(updateTradeDTO.getId())
                .salesman(salesman)
                .school(school)
                .signContractDate(updateTradeDTO.getSignContractDate())
                .note(updateTradeDTO.getNote())
                .build();

        tradeRepository.save(trade);
        Set<Trade> tradeBySchool = tradeRepository.findBySchoolRspo(updateTradeDTO.getSchoolId());

        return mapper.mapTradesToFullTradeBySchool(tradeBySchool);
    }

    public void deleteTradeById(Long id) {
        tradeRepository.deleteById(id);
    }

}
