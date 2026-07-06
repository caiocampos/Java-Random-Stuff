package br.campos.poi.model.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.campos.poi.model.transfer.POIDTO;
import br.campos.poi.util.Cartesian;
import br.campos.poi.util.DataType;
import lombok.Data;

/**
 * Documento mongo para Pontos de Interesse
 * 
 * @author Caio
 */
@Data
@Document(collection = "poi")
public class POIDocument extends br.campos.poi.model.abst.Document implements Serializable {
	private static final long serialVersionUID = 2625585029360206149L;
	/**
	 * Id do Objeto
	 */
	@Id
	private String id;
	/**
	 * Nome do ponto
	 */
	@NonNull
	@Indexed(unique = true)
	private String name;
	/**
	 * Localização [X,Y]
	 */
	@NonNull
	@GeoSpatialIndexed(max = Integer.MAX_VALUE, min = 0)
	private Double[] location;

	/**
	 * Construtor de Documento Mongo de Ponto de Interesse
	 */
	public POIDocument() {
		this.location = newPoint();
	}

	/**
	 * Construtor de Documento Mongo de Ponto de Interesse
	 * 
	 * @param dto DTO de Ponto de Interesse
	 */
	public POIDocument(br.campos.poi.model.abst.ObjectDTO dto) {
		POIDTO poi = (POIDTO) dto;
		this.location = newPoint();
		this.setLocation(poi.getX(), poi.getY());
		this.name = poi.getName();
	}

	/**
	 * Construtor de Documento Mongo de Ponto de Interesse
	 * 
	 * @param name Nome do ponto
	 * @param x    posição X do ponto
	 * @param y    posição Y do ponto
	 */
	public POIDocument(String name, Double x, Double y) {
		this.location = newPoint();
		this.setLocation(x, y);
		this.name = name;
	}

	/**
	 * Define o tipo de dado o TypedData possui
	 * 
	 * @return Enum com o tipo de dado
	 */
	@Override
	public DataType getType() {
		return DataType.POI_TYPE;
	}

	/**
	 * Altera a localização do Ponto
	 * 
	 * @param x posição X do ponto
	 * @param y posição X do ponto
	 */
	public void setLocation(Double x, Double y) {
		this.location[Cartesian.X.ordinal()] = x;
		this.location[Cartesian.Y.ordinal()] = y;
	}

	/**
	 * Cria a estrutura de Localização
	 * 
	 * @return estrutura [X,Y]
	 */
	protected Double[] newPoint() {
		return new Double[2];
	}

	/**
	 * Recupera a posição X do ponto
	 * 
	 * @return Posição X do ponto
	 */
	public Double getX() {
		return getLocation(Cartesian.X);
	}

	/**
	 * Recupera a posição Y do ponto
	 * 
	 * @return Posição Y do ponto
	 */
	public Double getY() {
		return getLocation(Cartesian.Y);
	}

	/**
	 * Recupera a posição X ou Y do ponto
	 * 
	 * @param c Enum que define X e Y
	 * @return Posição X ou Y do ponto
	 */
	private Double getLocation(Cartesian c) {
		try {
			return this.location[c.ordinal()];
		} catch (Exception e) {
			return 0D;
		}
	}
}
