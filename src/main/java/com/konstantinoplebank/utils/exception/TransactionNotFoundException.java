package com.konstantinoplebank.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Transaction not found")
public class TransactionNotFoundException extends RuntimeException {
}
