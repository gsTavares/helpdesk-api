package com.gustavo.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gustavo.helpdesk.services.DBService;

@Configuration
@Profile("test") // determina o profile (.properties) para a classe
public class TestConfig {
    
    @Autowired
    private DBService dbService;

    // Toda vez que o perfil de test estiver ativo, algumas instancias já serão inicializadas no banco
    
    @Bean // permite que o método execute
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }
    
}
