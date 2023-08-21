package guru.springframework.spring6restmvc.mappers;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto dto);
    BeerDto beerToBeerDto(Beer beer);
}
