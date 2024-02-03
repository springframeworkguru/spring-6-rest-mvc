package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH="/api/v1/beer";
    public  static final String BEER_PATH_ID=BEER_PATH+ "/{beerId}";

    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity patchBeer(@RequestBody Beer beer,@PathVariable("beerId") UUID beerId) {
        beerService.patchBeerById(beerId,beer);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity DeleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeerById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateBeer(@RequestBody Beer beer,@PathVariable("beerId") UUID beerId){
        beerService.updateBeer(beer,beerId);
     return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity saveNewBeer(@RequestBody Beer beer){
        Beer savedBeer=beerService.save(beer);
        HttpHeaders header=new HttpHeaders();
        header.add("Location",BEER_PATH + savedBeer.getId().toString());
        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public Beer getById(@PathVariable("beerId")UUID beerId){

        return beerService.getById(beerId);
    }

}