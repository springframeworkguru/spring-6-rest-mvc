package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mappers.BeerOrderMapper;
import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import guru.springframework.spring6restmvc.repositories.BeerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
@RequiredArgsConstructor
public class BeerOrderServiceJPA implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public Optional<BeerOrderDTO> getById(UUID beerOrderId) {
        return Optional.ofNullable(beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.findById(beerOrderId)
                .orElse(null)));
    }

    @Override
    public Page<BeerOrderDTO> listOrders(Integer pageNumber, Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return beerOrderRepository.findAll(pageRequest).map(beerOrderMapper::beerOrderToBeerOrderDto);
    }
}












