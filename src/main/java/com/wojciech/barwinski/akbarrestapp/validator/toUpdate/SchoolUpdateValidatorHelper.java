package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.FieldReportDTO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class SchoolUpdateValidatorHelper {

    static List<FieldReportDTO> validateStringFieldsAreNotNullAndAreNotEmpty(Method[] methods, Object object) {
        List<FieldReportDTO> reportFields = new ArrayList<>();
        for (Method method : methods) {
            if (isGetter(method)) {
                String fieldName = getFieldName(method.getName());
                Object data = invokeGetter(method, object);
                if (data instanceof String) {
                    reportFields.add(checkIfStringIsNotNullAndIsNotEmpty(fieldName, (String) data));
                }
            }
        }
        return reportFields;
    }

    static FieldReportDTO checkIfStringIsNotNullAndIsNotEmpty(String fieldName, String data) {
        FieldReportDTO fieldReport = new FieldReportDTO(fieldName);

        if (data != null && data.isEmpty()) {
            fieldReport.setStatus(ValidationStatus.ERROR);
            fieldReport.setComment("Field was changed but is empty");
        } else {
            fieldReport.setStatus(ValidationStatus.OK);
        }
        return fieldReport;
    }

    static private boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0;
    }

    static private String getFieldName(String methodName) {
        return methodName.substring(3);
    }

    static private Object invokeGetter(Method getter, Object school) {
        try {
            return getter.invoke(school);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
