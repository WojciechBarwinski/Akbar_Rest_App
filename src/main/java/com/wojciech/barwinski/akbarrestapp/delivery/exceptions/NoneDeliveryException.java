package com.wojciech.barwinski.akbarrestapp.delivery.exceptions;

import com.wojciech.barwinski.akbarrestapp.exception.ApplicationException;

public class NoneDeliveryException extends ApplicationException {

    public NoneDeliveryException(String message) {
        super(message);
    }
}
