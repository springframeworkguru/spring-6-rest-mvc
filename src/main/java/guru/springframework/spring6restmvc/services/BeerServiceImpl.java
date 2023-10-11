package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get beer by id - service");

        return Beer.builder()
                .id(id)
                .version(1)
                .bearName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
    }
}
