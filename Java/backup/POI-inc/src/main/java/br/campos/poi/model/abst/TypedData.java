package br.campos.poi.model.abst;

import br.campos.poi.util.DataType;

/**
 * Abstração de Objetos que podem ser convertidos pela AbstractDataFactory
 * 
 * @author Caio
 */
public abstract class TypedData {

	/**
	 * Define o tipo de dado o TypedData possui
	 * 
	 * @return Enum com o tipo de dado
	 */
	public DataType getType() {
		return DataType.NO_TYPE;
	}
}
