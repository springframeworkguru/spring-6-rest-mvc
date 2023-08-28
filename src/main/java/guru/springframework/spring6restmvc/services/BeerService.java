package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerDto;
import guru.springframework.spring6restmvc.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerService {

    Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    Boolean deleteById(UUID beerId);
}
