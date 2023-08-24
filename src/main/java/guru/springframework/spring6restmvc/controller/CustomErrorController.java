package guru.springframework.spring6restmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception) {

        List errorList = exception.getFieldErrors().stream()
            .map(fieldError -> {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                return errorMap;
            })
            .toList();

        return ResponseEntity.badRequest().body(errorList);
    }
}
