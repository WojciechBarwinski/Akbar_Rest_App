package com.wojciech.barwinski.akbarrestapp.delivery.controllers;

import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.CreatePhotoSessionDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.PhotoSessionsByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.PhotoSessionsBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.UpdatePhotoSessionDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.services.PhotoSessionServices;
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
@RequestMapping("/photoSession")
@RequiredArgsConstructor
public class PhotoSessionController {

    private final PhotoSessionServices service;

    @GetMapping(value = "/photographer/{id}")
    public PhotoSessionsByPhotographDTO getPhotoSessionsByPhotographId(@PathVariable Long id) {
        return service.getPhotoSessionByPhotographId(id);
    }

    @GetMapping(value = "/school/{id}")
    public PhotoSessionsBySchoolDTO getPhotoSessionsBySchoolId(@PathVariable Long id) {
        return service.getPhotoSessionBySchoolId(id);
    }

    @PostMapping(consumes = {"application/json"})
    public PhotoSessionsBySchoolDTO createPhotoSession(@Valid @RequestBody CreatePhotoSessionDTO createStaffDTO) {
        return service.createPhotoSession(createStaffDTO);
    }

    @PutMapping(value = "/{id}")
    public PhotoSessionsBySchoolDTO updatePhotoSession(@PathVariable Long id,
                                                       @Valid @RequestBody UpdatePhotoSessionDTO updateStaffDTO) {
        checkIfIdFromPathAdnFromDTOAreTheSame(id, updateStaffDTO.getId());

        return service.updatePhotoSession(updateStaffDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePhotoSession(@PathVariable Long id) {
        service.deletePhotoSessionById(id);

        return ResponseEntity.noContent().build();
    }

    private void checkIfIdFromPathAdnFromDTOAreTheSame(Long idFromPath, Long idFromDTO) {
        if (!idFromPath.equals(idFromDTO)) {
            throw new IdMismatchException("id from path and DTO are different!");
        }
    }
}
