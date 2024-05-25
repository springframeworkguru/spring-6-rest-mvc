package guru.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Builder
@Data
public class BeerOrderLineDTO {
    private UUID id;

    private Long version;
    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    private BeerDTO beer;

    private Integer orderQuantity;
    private Integer quantityAllocated;
}
