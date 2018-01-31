package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Application {
    public static void main(String[] argv) throws Exception {
        SpringApplication.run(Application.class, argv);
    }

}
