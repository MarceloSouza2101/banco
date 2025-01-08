package santos.souza.marcelo.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.Generated;

@Generated
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

}
