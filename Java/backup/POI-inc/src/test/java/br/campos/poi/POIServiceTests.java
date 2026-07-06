package br.campos.poi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.campos.poi.POIIncApplication;
import br.campos.poi.model.transfer.POIDTO;
import br.campos.poi.service.abst.IPOIService;
import br.campos.poi.util.POIException;

/**
 * Testes para o componente IPOIService e POIService
 * 
 * @author Caio
 */
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = POIIncApplication.class)
public class POIServiceTests {
	@Autowired
	IPOIService poiService;

	// Testes em create
	@Test
	public void create() throws POIException {
		final Double mOne = Double.valueOf(-1);
		final Double dValue = Double.valueOf(1435674.003);
		assertThrows(POIException.class, () -> {
			poiService.create(null);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, null, null);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, mOne, null);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, null, mOne);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("A", null, null);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("B", mOne, null);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("C", null, mOne);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("D", mOne, dValue);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("E", dValue, mOne);
			poiService.create(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("", dValue, dValue);
			poiService.create(dto);
		});
		// Desabilitado para base em atividade
		/*
		 * POIDTO dtoO = new POIDTO("F", dValue, dValue);
		 * assertNotNull(poiService.create(dtoO)); assertThrows(POIException.class, () ->
		 * { POIDTO dto = new POIDTO("F", dValue, dValue); poiService.create(dto); });
		 * dtoO = new POIDTO("G", 0D, 10D); assertNotNull(poiService.create(dtoO)); dtoO
		 * = new POIDTO("H", 10D, 0D); assertNotNull(poiService.create(dtoO));
		 */
	}

	// Testes em update
	@Test
	public void update() throws POIException {
		final Double mOne = Double.valueOf(-1);
		final Double dValue = Double.valueOf(1435674.003);
		assertThrows(POIException.class, () -> {
			poiService.update(null);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, null, null);
			poiService.update(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("", dValue, dValue);
			poiService.update(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("F", mOne, null);
			poiService.update(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("F", null, mOne);
			poiService.update(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("F", mOne, dValue);
			poiService.update(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("F", dValue, mOne);
			poiService.update(dto);
		});
		// Desabilitado para base em atividade
		/*
		 * assertThrows(POIException.class, () -> { POIDTO dto = new POIDTO("A", dValue,
		 * dValue); poiService.update(dto); }); POIDTO dtoO = new POIDTO("F", 1D, 10D);
		 * assertNotNull(poiService.update(dtoO));
		 */
	}

	// Testes em find e findDTO
	@Test
	public void findDTO() throws POIException {
		assertThrows(POIException.class, () -> {
			poiService.findDTO(null);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, null, null);
			poiService.findDTO(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("", null, null);
			poiService.findDTO(dto);
		});
		// Desabilitado para base em atividade
		/*
		 * assertThrows(POIException.class, () -> { POIDTO dto = new POIDTO("A", null,
		 * null); poiService.findDTO(dto); }); POIDTO dtoO = new POIDTO("H", null,
		 * null); assertNotEquals(poiService.findDTO(dtoO).toString(),
		 * "POIDTO(nome=H, x=10, y=0)");
		 * 
		 * assertTrue(poiService.findDTO(0D, 0D, 0D).isEmpty());
		 * assertTrue(poiService.findDTO(0D, 0D, 10D).size() == 2);
		 * 
		 * assertTrue(poiService.findDTO().size() == 3);
		 */
	}

	// Testes em delete
	@Test
	public void delete() throws POIException {
		assertThrows(POIException.class, () -> {
			poiService.delete(null);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO(null, null, null);
			poiService.delete(dto);
		});
		assertThrows(POIException.class, () -> {
			POIDTO dto = new POIDTO("", null, null);
			poiService.delete(dto);
		});
		// Desabilitado para base em atividade
		/*
		 * assertThrows(POIException.class, () -> { POIDTO dto = new POIDTO("A", null,
		 * null); poiService.delete(dto); }); POIDTO dtoO = new POIDTO("G", null, null);
		 * 
		 * poiService.delete(dtoO);
		 */
	}

	// Desabilitado para não alterar a base de dados
	// Testes em deleteAll
	// @Test
	public void deleteAll() throws POIException {
		poiService.deleteAll();
	}
}
