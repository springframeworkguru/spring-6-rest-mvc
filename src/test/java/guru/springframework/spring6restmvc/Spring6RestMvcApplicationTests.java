package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6RestMvcApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    TestController testController;

    @Test
    void testAutowiredController() {
        System.out.println(testController.sayHello());
    }

    @Test
    void testGetControllerFromCtx() {
        TestController testController = applicationContext.getBean(TestController.class);

        System.out.println(testController.sayHello());
    }

    @Test
    void contextLoads() {
    }

}
