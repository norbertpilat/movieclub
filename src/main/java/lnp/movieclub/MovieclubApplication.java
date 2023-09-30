package lnp.movieclub;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieclubApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieclubApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
