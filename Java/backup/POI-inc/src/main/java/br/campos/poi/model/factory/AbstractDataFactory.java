package br.campos.poi.model.factory;

import br.campos.poi.model.abst.Document;
import br.campos.poi.model.abst.ObjectDTO;
import br.campos.poi.model.document.POIDocument;
import br.campos.poi.model.transfer.POIDTO;

/**
 * Fábrica abstrata de DocumentFactory e ObjectDTOFactory
 * 
 * @author caio
 */
public abstract class AbstractDataFactory {

	/**
	 * Gera a fábrica adequada ao tipo de dado
	 * 
	 * @param dto Objeto de amostra para a identificação da fábrica necessária
	 * @return
	 */
	public static DocumentFactory getFactory(ObjectDTO dto) {
		switch (dto.getType()) {
		case POI_TYPE:
			return POIDocument::new;
		default:
			return null;
		}
	}

	/**
	 * Gera a fábrica adequada ao tipo de dado
	 * 
	 * @param dto Objeto de amostra para a identificação da fábrica necessária
	 * @return
	 */
	public static ObjectDTOFactory getFactory(Document doc) {
		switch (doc.getType()) {
		case POI_TYPE:
			return POIDTO::new;
		default:
			return null;
		}
	}
}
