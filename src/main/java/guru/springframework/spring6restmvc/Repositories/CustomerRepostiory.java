package guru.springframework.spring6restmvc.Repositories;

import guru.springframework.spring6restmvc.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepostiory extends JpaRepository<Customer, UUID> {
}
