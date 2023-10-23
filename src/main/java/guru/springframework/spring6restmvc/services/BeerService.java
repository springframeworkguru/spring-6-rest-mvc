package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.*;

import java.util.*;

public interface BeerService {
    Beer getBeerById(UUID id);
    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);
}
