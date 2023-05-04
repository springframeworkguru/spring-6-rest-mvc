package guru.springframework.spring6restmvc.mappers;

import guru.springframework.spring6restmvc.Customer.CustomerDto;
import guru.springframework.spring6restmvc.entites.Customer;
import org.mapstruct.Mapper;

@Mapper // This is a marker annotation so it declare that this is a mapper interface
public interface CustomerMapper {
//The way it converts is using the Builder pattern
    Customer CustomertoCustomerDTO(Customer customer);
    CustomerDto CustomerDTOtoCustomer(Customer customer); // remember DTO mean Data Transfer Object

    //Remember Mapping is converting DTO to entities and entities to DTO
//Remember the difference , Entities are like the toys in your toy box, they are different things
// that you play with and have their own special features.
// DTOs are like the boxes you use to carry toys around,they are used to move toys from one place to another, but
// they don't do anything special on their own.
//Entities are objects that represent a domain concept, such as a customer, product, or order.
// They are often used to model business rules and constraints, and are typically persisted in a database.
// Entities are part of the domain layer of an application and can contain business logic.

   // DTOs, on the other hand, are objects used to transfer data between layers of an application,
    // such as between the domain layer and the presentation layer. DTOs are often used to flatten complex entity structures,
    // reduce network traffic, and encapsulate data. They typically only contain data and have no business logic.

   // In summary, entities represent the domain model and contain business logic, while DTOs are used for data transfer and
    // contain only data.

    //That why it important to know how to convert DTO to entities and entities to DTO



















    //Also if you want mapstruct to work with lombak
    //Add this to maven
    // <plugin>
    //                <groupId>org.apache.maven.plugins</groupId>
    //                <artifactId>maven-compiler-plugin</artifactId>
    //                <version>3.10.1</version>
    //                <configuration>
    //                    <source>17</source>
    //                    <target>17</target>
    //                   <annotationProcessorPaths>
    //                        <path>
    //                            <groupId>org.mapstruct</groupId>
    //                            <artifactId>mapstruct-processor</artifactId>
    //                            <version>1.5.2.Final</version>
    //                        </path>
    //                       <path>
    //                           <groupId>org.projectlombok</groupId>
    //                           <artifactId>lombok</artifactId>
    //                           <version>1.18.20</version>
    //                          </path>
    //                       <path>
    //                        This is needed to make mapstruct work with lombak
    //                           <groupId>org.projectlombok</groupId>
    //                           <artifactId>lombok-mapstruct-binding</artifactId>
    //                           <version>0.2.0</version>
    //                          </path>
    //                    </annotationProcessorPaths>
    //                    <compilerArgs>
    //                        <compilerArg>
    //                        This is so that mapstruct can work with spring and you give it complier
    //                            -Amapstruct.defaultComponentModel=spring
    //                        </compilerArg>
    //                    </compilerArgs>
    //                </configuration>
    //            </plugin>

}
