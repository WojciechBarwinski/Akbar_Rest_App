package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomSchoolRepositoryImpl implements CustomSchoolRepository {

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

        predicates.addAll(createPredicatesForMainSchoolData(searchRequest, root, criteriaBuilder));
        predicates.addAll(createPredicatesForSchoolAddress(searchRequest, root, criteriaBuilder));
        predicates.addAll(createPredicatesForSchoolStatus(searchRequest, root, criteriaBuilder));


        if (predicates.isEmpty()) {
            return Collections.emptyList();
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(criteriaQuery).getResultList();
    }

    private List<Predicate> createPredicatesForMainSchoolData(SchoolSearchRequest searchRequest,
                                                              Root<School> root,
                                                              CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (isValueCorrect(searchRequest.getType())) {
            predicates.add(criteriaBuilder.like(
                    root.get("type"), "%" + searchRequest.getType() + "%"));
        }

        if (isValueCorrect(searchRequest.getSchoolName())) {
            predicates.add(criteriaBuilder.like(
                    root.get("name"), "%" + searchRequest.getSchoolName() + "%"));
        }

        if (isValueCorrect(searchRequest.getEmail())) {
            predicates.add(criteriaBuilder.like(
                    root.get("email"), "%" + searchRequest.getEmail() + "%"));
        }

        if (isValueCorrect(searchRequest.getPhoneNumber())) {
            predicates.add(createPhonePredicate(
                    criteriaBuilder, root, searchRequest.getPhoneNumber()));
        }

        return predicates;
    }

    private List<Predicate> createPredicatesForSchoolAddress(SchoolSearchRequest searchRequest,
                                                             Root<School> root,
                                                             CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (isValueCorrect(searchRequest.getVoivodeship())) {
            Voivodeship voivodeship = Voivodeship.getVoivodeshipByString(searchRequest.getVoivodeship());
            if (voivodeship != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("address").get("voivodeship"), voivodeship));
            }
        }

        if (isValueCorrect(searchRequest.getCounty())) {
            predicates.add(criteriaBuilder.equal(
                    root.get("address").get("county"), searchRequest.getCounty()));
        }

        if (isValueCorrect(searchRequest.getBorough())) {
            predicates.add(criteriaBuilder.equal(
                    root.get("address").get("borough"), searchRequest.getBorough()));
        }

        if (isValueCorrect(searchRequest.getCity())) {
            predicates.add(criteriaBuilder.equal(
                    root.get("address").get("city"), searchRequest.getCity()));
        }

        if (isValueCorrect(searchRequest.getStreet())) {
            predicates.add(criteriaBuilder.like(
                    root.get("address").get("street"), "%" + searchRequest.getStreet() + "%"));
        }

        return predicates;
    }

    private List<Predicate> createPredicatesForSchoolStatus(SchoolSearchRequest searchRequest,
                                                            Root<School> root,
                                                            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (isValueCorrect(searchRequest.getIsOurs())) {
            String isOurs = searchRequest.getIsOurs();
            if (isOurs.equals("true") || isOurs.equals("false")) {
                predicates.add(criteriaBuilder.equal(
                        root.get("additionalSchoolInformation").get("status").get("isOurs"), Boolean.valueOf(isOurs)));
            }
        }

        if (isValueCorrect(searchRequest.getIsContracted())) {
            String isContracted = searchRequest.getIsContracted();
            if (isContracted.equals("true") || isContracted.equals("false")) {
                predicates.add(criteriaBuilder.equal(
                        root.get("additionalSchoolInformation").get("status").get("isContracted"), Boolean.valueOf(isContracted)));
            }
        }

        if (isValueCorrect(searchRequest.getIsPhoto())) {
            String isPhoto = searchRequest.getIsPhoto();
            if (isPhoto.equals("true") || isPhoto.equals("false")) {
                predicates.add(criteriaBuilder.equal(
                        root.get("additionalSchoolInformation").get("status").get("isPhoto"), Boolean.valueOf(isPhoto)));
            }
        }

        if (isValueCorrect(searchRequest.getIsSettle())) {
            String isSettle = searchRequest.getIsSettle();
            if (isSettle.equals("true") || isSettle.equals("false")) {
                predicates.add(criteriaBuilder.equal(
                        root.get("additionalSchoolInformation").get("status").get("isSettle"), Boolean.valueOf(isSettle)));
            }
        }

        return predicates;
    }

    private Predicate createPhonePredicate(CriteriaBuilder criteriaBuilder,
                                           Root<School> root,
                                           String phoneNumber) {
        Join<School, Phone> phoneJoin = root.join("phones", JoinType.INNER);
        return criteriaBuilder.equal(phoneJoin.get("number"), phoneNumber);
    }

    private boolean isValueCorrect(String searchRequest) {
        return searchRequest != null && !searchRequest.trim().isEmpty();
    }
}
