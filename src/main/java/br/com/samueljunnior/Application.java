package br.com.samueljunnior;

import br.com.samueljunnior.config.LogFooter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final var context = SpringApplication.run(Application.class, args);
        if(context.isActive()){
            LogFooter.showLogFooter(context);
        }
    }

}
