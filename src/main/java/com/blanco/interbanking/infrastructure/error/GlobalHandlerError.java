package com.blanco.interbanking.infrastructure.error;

import com.blanco.interbanking.infrastructure.error.dto.ErrorResponseDTO;
import com.blanco.interbanking.infrastructure.error.enums.ErrorType;
import com.blanco.interbanking.infrastructure.exception.ResourceForbiddenException;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalHandlerError {

    private static final Logger log = LoggerFactory.getLogger(GlobalHandlerError.class);
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponseDTO>  handlePersistenceException(PersistenceException ex) {
        log(ex);
        return new ResponseEntity<>(createErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorType.TECHNICAL
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        log(ex);
        return new ResponseEntity<>(createErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ErrorType.BUSINESS
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceForbiddenException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(ResourceForbiddenException ex) {
        log(ex);
        return new ResponseEntity<>(createErrorResponse(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                ErrorType.TECHNICAL
        ), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log(ex);
        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        var globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        var errorMessage = Stream.of(fieldErrors, globalErrors)
                .filter(msg -> !msg.isEmpty())
                .collect(Collectors.joining(", "));

        var errorResponse = createErrorResponse(
                errorMessage,
                HttpStatus.BAD_REQUEST,
                ErrorType.BUSINESS
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponseDTO createErrorResponse(String detail, HttpStatus httpStatus, ErrorType errorType) {
        return new ErrorResponseDTO(
                detail,
                httpStatus.value(),errorType

        );
    }

    private void log(Exception e) {
        log.error(e.getMessage());

    }
}
