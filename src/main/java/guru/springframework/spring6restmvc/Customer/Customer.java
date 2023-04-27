package guru.springframework.spring6restmvc.Customer;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Customer {

    private String Customername;
    private UUID Customerid;

    private String CustomerVersion;
    private String CustomerDate;

    private String lastModfiedDate;

}
