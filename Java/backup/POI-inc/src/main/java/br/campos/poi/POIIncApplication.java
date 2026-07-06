package br.campos.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

/**
 * Classe principal da aplicação
 *
 * @author Caio
 */
@Controller
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "br.campos.poi.repository")
public class POIIncApplication {

	/**
	 * Inicia a aplicação
	 *
	 * @param args Argumentos passados na inicialização
	 */
	public static void main(String[] args) {
		SpringApplication.run(POIIncApplication.class, args);
	}

}
