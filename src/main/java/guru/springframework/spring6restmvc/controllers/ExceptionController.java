package guru.springframework.spring6restmvc.controllers;

import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(){
        log.debug("Beer not found");
        return ResponseEntity.notFound().build();
    }
}
