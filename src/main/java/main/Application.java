package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] argv) throws Exception {
        SpringApplication.run(Application.class, argv);
    }

    @RequestMapping("/hello")
    public String showHelloWorld() {
        return "hello-world";
    }

}
