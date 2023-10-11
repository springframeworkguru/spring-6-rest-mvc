package guru.springframework.spring6restmvc.model;

import lombok.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Data
@Builder
public class Beer {
    private UUID id;
    private Integer version;
    private String bearName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
