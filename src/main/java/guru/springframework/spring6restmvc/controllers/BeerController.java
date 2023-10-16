package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.model.*;
import guru.springframework.spring6restmvc.services.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@AllArgsConstructor
@RestController()
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    public Beer getBeerById(UUID id){
        log.debug("Get beer by id - controller");
        return  beerService.getBeerById(id);
    }

}
