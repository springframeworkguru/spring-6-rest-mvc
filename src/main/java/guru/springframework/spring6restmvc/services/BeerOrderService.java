package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerOrderService {

    Optional<BeerOrderDTO> getById(UUID beerOrderId);

    Page<BeerOrderDTO> listOrders(Integer pageNumber, Integer pageSize);
}
