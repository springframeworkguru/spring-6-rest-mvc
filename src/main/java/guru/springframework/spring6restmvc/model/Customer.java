package guru.springframework.spring6restmvc.model;

import lombok.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Data
@Builder
public class Customer {
    private UUID id;
    private Integer version;
    private String customerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
