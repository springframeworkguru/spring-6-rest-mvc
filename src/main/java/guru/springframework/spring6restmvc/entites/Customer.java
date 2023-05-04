package guru.springframework.spring6restmvc.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;


import java.util.UUID;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer  {
@Id
//Below is to generate UUID since it not unable to self generate so we are using hibernate to generate UUID
@GeneratedValue (generator = "UUID") // this is from the JPA api and not from hibernate
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") // this is from hibernate
// and we put here what generate we want to use
@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
//Above is giving Hibernate the instruction to generate UUID and not to update it ever and not to make
// it null
//It going to make a varchar (36)
    private UUID Customerid;
    private String Customername;
@Version
    private Integer CustomerVersion;
    private String CustomerDate;

    private String lastModfiedDate;



}
