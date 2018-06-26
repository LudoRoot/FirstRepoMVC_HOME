package fr.formation.config;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath:application-context.xml")
@ComponentScan(basePackages = { "fr.formation.dao" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "fr.formation.dao" })
public class AppConfig {
    //
}
