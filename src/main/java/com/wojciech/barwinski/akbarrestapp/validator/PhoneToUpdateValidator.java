package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.dtos.PhoneDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.FieldReportDTO;

import java.util.ArrayList;
import java.util.List;

public class PhoneToUpdateValidator {

    static List<FieldReportDTO> validatePhones(List<PhoneDTO> phones) {
        List<FieldReportDTO> reportFields = new ArrayList<>();

        for (PhoneDTO phone : phones) {
            if (phone.isToRemove()) {
                continue;
            }
            FieldReportDTO fieldReport = new FieldReportDTO("Phone ID " + phone.getId());
            if (checkCorrectPhoneData(phone)) {
                fieldReport.setStatus(ValidationStatus.ERROR);
                fieldReport.setComment("Phone was changed but is empty");
            } else {
                fieldReport.setStatus(ValidationStatus.OK);
            }
        }

        reportFields.add(checkIfThereIsOnlyOneMainPhone(phones));
        return reportFields;
    }

    static private FieldReportDTO checkIfThereIsOnlyOneMainPhone(List<PhoneDTO> phones) {
        FieldReportDTO fieldReport = new FieldReportDTO("Phones");

        long numberOfMainPhone = phones.stream()
                .filter(PhoneDTO::isMain)
                .count();

        if (numberOfMainPhone != 1) {
            fieldReport.setStatus(ValidationStatus.ERROR);
            fieldReport.setComment("There must by only 1 main phone");
        } else {
            fieldReport.setStatus(ValidationStatus.OK);
        }

        return fieldReport;
    }

    static private boolean checkCorrectPhoneData(PhoneDTO phone) {
        return (phone.getPhone() != null && phone.getPhone().isEmpty()) ||
                (phone.getOwner() != null && phone.getOwner().isEmpty()) ||
                (phone.getPhoneNote() != null && phone.getPhoneNote().isEmpty());

    }
}
