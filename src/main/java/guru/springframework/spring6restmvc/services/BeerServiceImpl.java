package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID,Beer> beerMap;

    public BeerServiceImpl(){
        this.beerMap= new HashMap<>();

        Beer beer1=Beer.builder()
                .id(UUID.randomUUID())
                .upc("h")
                .price(new BigDecimal(12.3))
                .version(2)
                .beerName("asmara beer")
                .quantityOnHand(123)
                .beerStyle(BeerStyle.ALE)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2=Beer.builder()
                .id(UUID.randomUUID())
                .upc("h")
                .price(new BigDecimal(12.3))
                .version(2)
                .beerName("asmara beer")
                .quantityOnHand(123)
                .beerStyle(BeerStyle.ALE)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3=Beer.builder()
                .id(UUID.randomUUID())
                .upc("h")
                .price(new BigDecimal(12.3))
                .version(2)
                .beerName("asmara beer")
                .quantityOnHand(123)
                .beerStyle(BeerStyle.ALE)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);
    }

    @Override
    public Beer getById(UUID Id) {
        return beerMap.get(Id);
    }

    @Override
    public List<Beer> listBeers(){
        return new  ArrayList<>(beerMap.values());
    }

    @Override
    public Beer save(Beer beer) {

        Beer savedBeer=Beer.builder()
                .id(UUID.randomUUID())
                .upc(beer.getUpc())
                .price(new BigDecimal(12.3))
                .version(2)
                .beerName(beer.getBeerName())
                .quantityOnHand(123)
                .beerStyle(BeerStyle.ALE)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
          beerMap.put(savedBeer.getId(),savedBeer);
        return savedBeer;
    }

    @Override
    public void updateBeer(Beer beer, UUID beerId) {
         Beer existingBeer=beerMap.get(beerId);
         existingBeer.setBeerName(beer.getBeerName());
         existingBeer.setPrice(beer.getPrice());
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

    }


}