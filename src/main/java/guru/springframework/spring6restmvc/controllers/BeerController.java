package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.services.*;
import lombok.*;
import org.springframework.stereotype.*;

@Controller
@AllArgsConstructor
public class BeerController {
    private final BeerService beerService;

}
