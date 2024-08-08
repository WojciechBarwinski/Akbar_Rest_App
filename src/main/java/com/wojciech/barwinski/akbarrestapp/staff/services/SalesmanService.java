package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.mappers.StaffMappers;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.SalesmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SalesmanService {

    private final SalesmanRepository repository;
    private final StaffMappers mappers;

    public SalesmanDTO getSalesmanById(Long id) {
        Salesman salesman = repository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));
        return mappers.mapSalesmanToDTO(salesman);
    }

    public SalesmanDTO getSalesmanByLastname(String lastname) {
        Salesman salesman = repository.findByLastName(lastname)
                .orElseThrow(() -> new StaffNotFoundException(lastname));
        return mappers.mapSalesmanToDTO(salesman);
    }

    public SalesmanDTO createSalesman(CreateStaffDTO createStaffDTO) {
        Salesman salesman = mappers.mapNewStaffDTOToSalesman(createStaffDTO);

        Salesman createdSalesman = repository.save(salesman);

        return mappers.mapSalesmanToDTO(createdSalesman);
    }
}
