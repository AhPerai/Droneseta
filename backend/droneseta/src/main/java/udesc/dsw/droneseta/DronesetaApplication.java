package udesc.dsw.droneseta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import udesc.dsw.droneseta.model.entity.Admin;
import udesc.dsw.droneseta.repository.AdminRepository;
import udesc.dsw.droneseta.repository.ClienteRepository;
import udesc.dsw.droneseta.repository.ProdutoRepository;

@SpringBootApplication
@EnableScheduling
public class DronesetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesetaApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configCORS() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}
}
