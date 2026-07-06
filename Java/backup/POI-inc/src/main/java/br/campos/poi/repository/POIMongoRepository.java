package br.campos.poi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.campos.poi.model.document.POIDocument;

/**
 * Repositório de Pontos de Interesse
 * 
 * @author Caio
 */
public interface POIMongoRepository extends MongoRepository<POIDocument, String> {

	/**
	 * Busca por nome
	 * 
	 * @param name nome do Ponto de Interesse
	 * @return Ponto de Interesse
	 */
	POIDocument findByName(String name);

	/**
	 * Busca por Pontos de Interesse próximos
	 * 
	 * @param x        posição X do ponto
	 * @param y        posição Y do ponto
	 * @param distance distancia a procurar
	 * @return lista de Pontos de Interesse
	 */
	@Query("{ 'location': { $near: [ ?0, ?1 ], $maxDistance: ?2} }")
	List<POIDocument> findByLocationDistance(Double x, Double y, Double distance);
}
