package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getById(UUID Id);
    List <Beer> listBeers();

    Beer save(Beer beer);

    void updateBeer(Beer beer, UUID beerId);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}