package guru.springframework.spring6restmvc.listeners;

import guru.springframework.spring6restmvc.events.BeerCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
public class BeerCreatedListener {

    @EventListener
    public void listen(BeerCreatedEvent event) {
        System.out.println("I heard a beer was created!");
        System.out.println(event.getBeer().getId());

        //todo impl - add real implementation to persist audit record
    }
}
