package br.com.samueljunnior.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class LogFooter {

    public static void showLogFooter(ConfigurableApplicationContext context){
        ConfigurableEnvironment env = context.getEnvironment();

        String serverPort = env.getProperty("server.port");
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n\n***\n\tAplicação {} iniciada com sucesso!\n\tSwagger: http://localhost:{}/swagger-ui.html\n\tDesenvolvido por: Samuel Junnior \n***\n\n", applicationName, serverPort);
    }
}