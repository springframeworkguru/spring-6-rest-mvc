package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static guru.springframework.spring6restmvc.model.BeerStyle.PALE_ALE;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer Id in service was called");

        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .beerstyle(PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
