package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.*;

import java.math.*;
import java.time.*;
import java.util.*;

public class BearServiceImpl implements BearService {
    @Override
    public Beer getBearById(UUID id) {
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
