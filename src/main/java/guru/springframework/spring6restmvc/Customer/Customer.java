package guru.springframework.spring6restmvc.Customer;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private String Customername;
    private int Customerid;

    private String CustomerVersion;
    private String CustomerDate;

    private String lastModfiedDate;

}
