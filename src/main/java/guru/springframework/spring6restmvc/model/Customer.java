package guru.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Customer {

    private UUID Id;
    private String customerName;
    private BigDecimal version;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;



}