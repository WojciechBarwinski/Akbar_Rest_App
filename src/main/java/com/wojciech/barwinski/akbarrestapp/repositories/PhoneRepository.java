package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
