package br.com.fiap.coletas.config;

import br.com.fiap.coletas.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        String fieldName = ((FieldError) error).getField();
                        String defaultMessage = error.getDefaultMessage();
                        return String.format("Field '%s': %s", fieldName, defaultMessage);
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.joining("; "));

        return new ErrorResponseDto(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Dados fornecidos estão em formato inválido.";
        return new ErrorResponseDto(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleGeneralExceptions(Exception ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

}
