package himedia.spring.ver3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class Ver3SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ver3SecurityApplication.class, args);
	}

}
