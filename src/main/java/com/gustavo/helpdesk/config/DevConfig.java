package com.gustavo.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gustavo.helpdesk.services.DBService;

@Configuration
@Profile("dev") // determina o profile (.properties) para a classe
public class DevConfig {
    
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}") /**
        Atribui o valor de uma propriedade contida no profile espeficificado acima (no caso, em "dev")
        para a variável abaixo da anotação
    */
    private String value;

    @Bean
    public boolean instanciaDb() {
        if(value.equals("create")) { // Se o valor for create, salva as primeiras entidades no banco
            this.dbService.instanciaDB();
        }
        return false;
    }

}
