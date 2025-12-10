package com.pedro_a10.LojaAPI.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Map<Class<? extends Throwable>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
    UserNotFoundException.class, HttpStatus.NOT_FOUND,
    ProductNotFoundException.class, HttpStatus.NOT_FOUND,
    UserCpfNotFoundException.class, HttpStatus.NOT_FOUND,
    EmailAlreadyExistsException.class, HttpStatus.CONFLICT,
    ProductInvalidQuantityException.class, HttpStatus.BAD_REQUEST
  );

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, Throwable ex, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
      LocalDateTime.now(),
      status.value(),
      status.getReasonPhrase(),
      ex.getMessage() + " | Path: " + request.getRequestURI()
    );
    return new ResponseEntity<>(error, status);
  }
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions(Throwable ex, HttpServletRequest request) {
    HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    return buildResponse(status, ex, request);
  }
}
