package com.wojciech.barwinski.akbarrestapp.mappers.repositories;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
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
    public List<ShortSchoolDTO> findSchoolBySearchRequest(SchoolSearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ShortSchoolDTO> criteriaQuery = criteriaBuilder.createQuery(ShortSchoolDTO.class);

        Root<School> root = criteriaQuery.from(School.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<School, Phone> phoneJoin = root.join("phones", JoinType.LEFT);


        Predicate mainPhonePredicate = criteriaBuilder.isTrue(phoneJoin.get("isMain"));
        Predicate nullPhonePredicate = criteriaBuilder.isNull(phoneJoin.get("number"));
        Predicate phoneIsMainOrIsNull = criteriaBuilder.or(mainPhonePredicate, nullPhonePredicate);


        criteriaQuery.multiselect(
                root.get("rspo"),
                root.get("name"),
                root.get("address").get("voivodeship"),
                root.get("address").get("county"),
                root.get("address").get("borough"),
                root.get("address").get("city"),
                root.get("address").get("street"),
                phoneJoin.get("number")
        );

        predicates.addAll(createPredicatesForMainSchoolData(searchRequest, root, criteriaBuilder));
        predicates.addAll(createPredicatesForSchoolAddress(searchRequest, root, criteriaBuilder));
        predicates.addAll(createPredicatesForSchoolStatus(searchRequest, root, criteriaBuilder));

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        }

        predicates.add(phoneIsMainOrIsNull);
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


        if (searchRequest.getIsOurs() != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("additionalSchoolInformation").get("status").get("isOurs"), searchRequest.getIsOurs()));
        }

        if (searchRequest.getIsContracted() != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("additionalSchoolInformation").get("status").get("isContracted"), searchRequest.getIsContracted()));
        }

        if (searchRequest.getIsPhoto() != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("additionalSchoolInformation").get("status").get("isPhoto"), searchRequest.getIsPhoto()));
        }

        if (searchRequest.getIsSettle() != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("additionalSchoolInformation").get("status").get("isSettle"), searchRequest.getIsSettle()));
        }

        return predicates;
    }

    private Predicate createPhonePredicate(CriteriaBuilder criteriaBuilder,
                                           Root<School> root,
                                           String phoneNumber) {
        Subquery<School> subQuery = criteriaBuilder.createQuery().subquery(School.class);
        Root<School> subRoot = subQuery.correlate(root);
        Join<School, Phone> phoneJoin = subRoot.join("phones", JoinType.INNER);
        subQuery.select(subRoot);
        subQuery.where(criteriaBuilder.equal(phoneJoin.get("number"), phoneNumber));

        return criteriaBuilder.exists(subQuery);
    }

    private boolean isValueCorrect(String searchRequest) {
        return searchRequest != null && !searchRequest.trim().isEmpty();
    }
}
