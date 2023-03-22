package guru.springframework.spring6restmvc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Beer {
    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerstyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
