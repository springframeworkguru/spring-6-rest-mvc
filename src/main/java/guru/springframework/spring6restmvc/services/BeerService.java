package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerService {

    List<BeerDto> listBeers(String beerName);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    Boolean deleteById(UUID beerId);
}
