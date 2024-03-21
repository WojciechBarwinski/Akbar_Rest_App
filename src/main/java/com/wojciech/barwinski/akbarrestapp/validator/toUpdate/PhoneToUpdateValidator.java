package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import com.wojciech.barwinski.akbarrestapp.dtos.PhoneToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.FieldReportDTO;

import java.util.ArrayList;
import java.util.List;

public class PhoneToUpdateValidator {

    static List<FieldReportDTO> validatePhones(List<PhoneToUpdateDTO> phones) {
        List<FieldReportDTO> reportFields = new ArrayList<>();

        for (PhoneToUpdateDTO phone : phones) {
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

    static private FieldReportDTO checkIfThereIsOnlyOneMainPhone(List<PhoneToUpdateDTO> phones) {
        FieldReportDTO fieldReport = new FieldReportDTO("Phones");

        long numberOfMainPhone = phones.stream()
                .filter(PhoneToUpdateDTO::isMain)
                .count();

        if (numberOfMainPhone != 1) {
            fieldReport.setStatus(ValidationStatus.ERROR);
            fieldReport.setComment("There must by only 1 main phone");
        } else {
            fieldReport.setStatus(ValidationStatus.OK);
        }

        return fieldReport;
    }

    static private boolean checkCorrectPhoneData(PhoneToUpdateDTO phone) {
        return (phone.getNumber() != null && phone.getNumber().isEmpty()) ||
                (phone.getOwner() != null && phone.getOwner().isEmpty()) ||
                (phone.getPhoneNote() != null && phone.getPhoneNote().isEmpty());

    }
}
