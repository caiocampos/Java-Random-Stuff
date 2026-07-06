package br.campos.poi.model.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de busca de Pontos de Interesse próximos
 * 
 * @author Caio
 */
@Data
@NoArgsConstructor
public class POINearDTO {
	/**
	 * Posição X do ponto
	 */
	Double x;
	/**
	 * Posição Y do ponto
	 */
	Double y;
	/**
	 * Distancia a procurar
	 */
	Double distance;

	/**
	 * Construtor de DTO de busca de Pontos de Interesse próximos
	 * 
	 * @param x        posição X do ponto
	 * @param y        posição Y do ponto
	 * @param distance distancia a procurar
	 */
	@JsonCreator
	public POINearDTO(@JsonProperty("x") Double x, @JsonProperty("y") Double y,
			@JsonProperty("distance") Double distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}
