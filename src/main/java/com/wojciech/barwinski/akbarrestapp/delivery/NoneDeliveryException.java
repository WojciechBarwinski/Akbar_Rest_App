package com.wojciech.barwinski.akbarrestapp.delivery;

import com.wojciech.barwinski.akbarrestapp.exception.ApplicationException;

public class NoneDeliveryException extends ApplicationException {

    public NoneDeliveryException(String message) {
        super(message);
    }
}
