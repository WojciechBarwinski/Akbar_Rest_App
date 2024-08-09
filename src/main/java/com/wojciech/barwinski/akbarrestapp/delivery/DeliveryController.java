package com.wojciech.barwinski.akbarrestapp.delivery;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Akbar API - deliveries")
@RestController()
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService service;

    @GetMapping(value = "/{id}")
    void getPhotoSessions(@PathVariable Long id){
        service.getPhotoSessionByPhotographId(id);
    }
}
