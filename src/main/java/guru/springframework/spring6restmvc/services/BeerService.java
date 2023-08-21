package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerService {

    List<BeerDto> listBeers();

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    void updateBeerById(UUID beerId, BeerDto beer);

    void deleteById(UUID beerId);
}
