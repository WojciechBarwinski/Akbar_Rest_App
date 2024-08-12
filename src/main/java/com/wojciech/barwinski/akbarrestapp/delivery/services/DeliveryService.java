package com.wojciech.barwinski.akbarrestapp.delivery.services;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.delivery.repositories.PhotoSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final PhotoSessionRepository repository;

    public void getPhotoSessionByPhotographId(Long id){
        Set<PhotoSession> byPhotographerId = repository.findByPhotographerId(id);
    }
}
