package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.model.*;
import guru.springframework.spring6restmvc.services.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;
@Slf4j
@Controller
@AllArgsConstructor
public class BeerController {
    private final BeerService beerService;
    public Beer getBeerById(UUID id){

        log.debug("Get beer by id - controller");

        return  beerService.getBeerById(id);
    }

}
