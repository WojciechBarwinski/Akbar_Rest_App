package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomSchoolRepositoryImpl implements CustomSchoolRepository{

   private final EntityManager em;

    public CustomSchoolRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<School> findSchoolBySearchRequest(SchoolSearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<School> criteriaQuery = criteriaBuilder.createQuery(School.class);

        Root<School> root = criteriaQuery.from(School.class);
        List<Predicate> predicates = new ArrayList<>();

        if (isValueCorrect(searchRequest.getSchoolName())){
            predicates.add(criteriaBuilder.like(
                    root.get("name"), "%" + searchRequest.getSchoolName() + "%"));
        }

        if (isValueCorrect(searchRequest.getEmail())){
            predicates.add(criteriaBuilder.like(
                    root.get("email"), "%" + searchRequest.getEmail() + "%"));
        }

        if (isValueCorrect(searchRequest.getPhoneNumber())){
            predicates.add(createPhonePredicate(
                    criteriaBuilder, root, searchRequest.getPhoneNumber()));
        }





        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }

    private static boolean isValueCorrect(String searchRequest) {
        return searchRequest != null && !searchRequest.trim().isEmpty();
    }

    private Predicate createPhonePredicate(CriteriaBuilder criteriaBuilder, Root<School> root, String phoneNumber) {
        Join<School, Phone> phoneJoin = root.join("phones", JoinType.INNER);
        return criteriaBuilder.equal(phoneJoin.get("number"), phoneNumber);
    }
}
