package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
