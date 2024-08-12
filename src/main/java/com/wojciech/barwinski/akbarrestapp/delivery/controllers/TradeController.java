package com.wojciech.barwinski.akbarrestapp.delivery.controllers;


import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.CreateTradeDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.UpdateTradeDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.services.TradeService;
import com.wojciech.barwinski.akbarrestapp.exception.IdMismatchException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Akbar API - deliveries")
@RestController()
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService service;


    @GetMapping(value = "/school/{id}")
    public FullTradesBySchoolDTO getTradesBySchoolId(@PathVariable Long id) {
        return service.getTradesBySchoolId(id);
    }

    @GetMapping(value = "/salesman/{id}")
    public FullTradesBySalesmanDTO getTradesBySalesmanId(@PathVariable Long id) {
        return service.getTradesBySalesmanId(id);
    }

    @PostMapping(consumes = {"application/json"})
    public FullTradesBySchoolDTO createTrade(@Valid @RequestBody CreateTradeDTO createTradeDTO) {
        return service.createTrade(createTradeDTO);
    }


    @PutMapping(value = "/{id}")
    public FullTradesBySchoolDTO updateTrade(@PathVariable Long id,
                                             @Valid @RequestBody UpdateTradeDTO updateTradeDTO) {
        checkIfIdFromPathAdnFromDTOAreTheSame(id, updateTradeDTO.getId());

        return service.updateTrade(updateTradeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        service.deleteTradeById(id);

        return ResponseEntity.noContent().build();
    }

    private void checkIfIdFromPathAdnFromDTOAreTheSame(Long idFromPath, Long idFromDTO) {
        if (!idFromPath.equals(idFromDTO)) {
            throw new IdMismatchException("id from path and DTO are different!");
        }
    }
}
