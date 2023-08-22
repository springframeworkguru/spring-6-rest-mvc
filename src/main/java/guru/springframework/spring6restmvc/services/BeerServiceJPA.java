package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.model.BeerDto;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDto> listBeers() {
        return null;
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beer) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDto beer) {

    }

    @Override
    public void deleteById(UUID beerId) {

    }
}
