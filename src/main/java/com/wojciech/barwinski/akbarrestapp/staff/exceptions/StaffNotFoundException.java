package com.wojciech.barwinski.akbarrestapp.staff.exceptions;

import com.wojciech.barwinski.akbarrestapp.exception.ApplicationException;

public class StaffNotFoundException extends ApplicationException {

    public StaffNotFoundException(String message) {
        super("Can't find staff with id/lastname: " + message);
    }
}
