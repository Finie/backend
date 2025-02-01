package com.spring.backend.advice;


import com.spring.backend.dto.NetworkResponseDTO;
import com.spring.backend.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@RestControllerAdvice
public class NetworkExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<NetworkResponseDTO<Void>> handleInternalServerError(Exception exception, WebRequest request) {

        NetworkResponseDTO<Void> networkResponseDTO = new NetworkResponseDTO<>();
        networkResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        networkResponseDTO.setError("Internal Server Error");
        networkResponseDTO.setMessage(exception.getMessage());
        networkResponseDTO.setData(null);

        return new ResponseEntity<>(networkResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<NetworkResponseDTO<Void>> handleResourceNotFound(ResourceNotFoundException exception, WebRequest request) {

        NetworkResponseDTO<Void> responseDTO = new NetworkResponseDTO<>();
        responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
        responseDTO.setError("Resource not found");
        responseDTO.setData(null);
        responseDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NetworkResponseDTO<Void>> handleClientInputValidation(MethodArgumentNotValidException exception, WebRequest request) {

        NetworkResponseDTO<Void> responseDTO = new NetworkResponseDTO<>();
        responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        responseDTO.setError("Bad request");
        responseDTO.setMessage(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<NetworkResponseDTO<Void>> constraintViolationHandler(DataIntegrityViolationException exception, WebRequest request){

        String detailedMessage = exception.getCause().getMessage();


        if (detailedMessage != null && detailedMessage.contains("Unique index or primary key violation")) {
            detailedMessage = "Email is already in use. Please use a different email address.";
        }

        NetworkResponseDTO<Void> responseDTO = new NetworkResponseDTO<>();
        responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        responseDTO.setError("Bad request");
        responseDTO.setMessage(detailedMessage);
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }



}
