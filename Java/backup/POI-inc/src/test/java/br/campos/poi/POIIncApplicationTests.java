package br.campos.poi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.campos.poi.POIIncApplication;

/**
 * Teste de inicialização
 * 
 * @author Caio
 */
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = POIIncApplication.class)
public class POIIncApplicationTests {

	@Test
	public void contextLoads() {
	}

}
