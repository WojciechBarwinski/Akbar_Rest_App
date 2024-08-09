package com.wojciech.barwinski.akbarrestapp.delivery;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final PhotoSessionRepository repository;

    void getPhotoSessionByPhotographId(Long id){
        Set<PhotoSession> byPhotographerId = repository.findByPhotographerId(id);
    }
}
