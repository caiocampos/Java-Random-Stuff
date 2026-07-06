package br.campos.poi.model.transfer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.campos.poi.model.abst.ObjectDTO;
import br.campos.poi.model.document.POIDocument;
import br.campos.poi.util.DataType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Pontos de Interesse
 * 
 * @author Caio
 */
@Data
@NoArgsConstructor
public class POIDTO extends ObjectDTO implements Serializable {
	private static final long serialVersionUID = -4827112214578752089L;
	/**
	 * Nome do ponto
	 */
	private String name;
	/**
	 * Posição X do ponto
	 */
	private Double x;
	/**
	 * Posição Y do ponto
	 */
	private Double y;

	/**
	 * Construtor de DTO de Ponto de Interesse
	 * 
	 * @param doc Documento Mongo de Ponto de Interesse
	 */
	public POIDTO(br.campos.poi.model.abst.Document doc) {
		POIDocument poi = (POIDocument) doc;
		this.name = poi.getName();
		this.x = poi.getX();
		this.y = poi.getY();
	}

	/**
	 * Construtor de DTO de Ponto de Interesse
	 * 
	 * @param name Nome do ponto
	 * @param x    posição X do ponto
	 * @param y    posição Y do ponto
	 */
	@JsonCreator
	public POIDTO(@JsonProperty("name") String name, @JsonProperty("x") Double x, @JsonProperty("y") Double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	/**
	 * Define o tipo de dado o TypedData possui
	 * 
	 * @return Enum com o tipo de dado
	 */
	@JsonIgnore
	@Override
	public DataType getType() {
		return DataType.POI_TYPE;
	}
}
