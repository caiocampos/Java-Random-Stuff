package br.campos.poi.model.factory;

import br.campos.poi.model.abst.Document;
import br.campos.poi.model.abst.ObjectDTO;

/**
 * Fábrica de DTOs
 * 
 * @author Caio
 */
@FunctionalInterface
public interface ObjectDTOFactory {

	/**
	 * Cria um DTO a partir de um Documento
	 * 
	 * @param doc Documento Mongo
	 * @return
	 */
	ObjectDTO create(Document doc);
}
