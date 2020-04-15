package by.chekun.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "by.chekun.model")
@EnableJpaRepositories(basePackages = "by.chekun")
@EnableTransactionManagement
public class AppTestConfig {


}
