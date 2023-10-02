package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllPhoneBySchoolRspo(Long schoolRspo);
}
