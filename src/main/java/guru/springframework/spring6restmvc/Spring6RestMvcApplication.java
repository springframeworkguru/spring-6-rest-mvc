package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring6RestMvcApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Spring6RestMvcApplication.class, args);

        TestController controller = ctx.getBean(TestController.class);

        System.out.println("In main method");
        System.out.println(controller.sayHello());
    }

}
