package com.greenhouse.sensormanagementservice.exceptions;

import com.greenhouse.sensormanagementservice.dto.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    private final String businessErrorMessage = "BUSINESS_ERROR";
    private final String databaseErrorMessage = "DATABASE_ERROR";

    private final String duplicateEntryMessage = "Data conflict or duplicate entry.";

    // Business exceptions.
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex) {
        ErrorDTO error = new ErrorDTO(businessErrorMessage, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // 400
    }

    // Data access exceptions.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataError(DataIntegrityViolationException ex) {
        ErrorDTO error = new ErrorDTO(databaseErrorMessage, duplicateEntryMessage);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409
    }

}
