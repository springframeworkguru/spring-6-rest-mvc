package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import guru.springframework.spring6restmvc.services.BeerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@RestController
@RequiredArgsConstructor
public class BeerOrderController {

    public static final String BEER_ORDER_PATH = "/api/v1/beerorder";
    public static final String BEER_ORDER_PATH_ID = BEER_ORDER_PATH + "/{beerOrderId}";

    private final BeerOrderService beerOrderService;

    @GetMapping(BEER_ORDER_PATH_ID)
    public BeerOrderDTO getBeerOrderById(@PathVariable UUID beerOrderId) {
        return beerOrderService.getById(beerOrderId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(BEER_ORDER_PATH)
    public Page<BeerOrderDTO> listOrders(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return beerOrderService.listOrders(pageNumber, pageSize);
    }
}














