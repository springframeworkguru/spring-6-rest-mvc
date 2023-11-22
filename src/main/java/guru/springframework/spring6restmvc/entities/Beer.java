package guru.springframework.spring6restmvc.entities;

import guru.springframework.spring6restmvc.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Beer {
    @Id
    private UUID id;

    @Version
    private Integer version;

    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
