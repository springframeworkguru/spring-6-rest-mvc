package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.bootstrap.BootstrapData;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName() {
        Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);

        assertThat(list).hasSize(336);
    }

    @Test
    void testGetBeerListByStyle() {
        Page<Beer> list = beerRepository.findAllByBeerStyle(BeerStyle.IPA, null);

        assertThat(list.getContent().size()).isEqualTo(548);
    }

    @Test
    void testGetBeerListByNameAndStyle() {
        Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%Larry Imperial%", BeerStyle.IPA, null);

        assertThat(list).hasSize(1);
    }


    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                                                .beerName("Mosaic Lager")
                                                .beerStyle(BeerStyle.LAGER)
                                                .upc("123456789")
                                                .price(new BigDecimal("3.39"))
                                                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖabcdefghijklmnopqrstuvwxyzåäö1234567890-_.,;:")
                    .beerStyle(BeerStyle.LAGER)
                    .upc("123456789")
                    .price(new BigDecimal("3.39"))
                    .build());

            beerRepository.flush();
        });
    }
}