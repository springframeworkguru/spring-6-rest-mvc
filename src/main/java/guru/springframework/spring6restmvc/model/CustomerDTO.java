package guru.springframework.spring6restmvc.model;

import lombok.*;

import java.time.*;
import java.util.*;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private Integer version;
    private String customerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
