package br.campos.poi.service.abst;

import java.util.List;

import br.campos.poi.model.document.POIDocument;
import br.campos.poi.model.transfer.POIDTO;
import br.campos.poi.util.POIException;

/**
 * Interface de Serviço de Pontos de Interesse
 * 
 * @author Caio
 */
public interface IPOIService {

	/**
	 * Cria um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento gerado
	 * @throws POIException
	 */
	POIDocument create(POIDTO dto) throws POIException;

	/**
	 * Atualiza um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento gerado
	 * @throws POIException
	 */
	POIDocument update(POIDTO dto) throws POIException;

	/**
	 * Busca um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento encontrado
	 * @throws POIException
	 */
	POIDocument find(POIDTO dto) throws POIException;

	/**
	 * Busca por Pontos próximos a um ponto de referência
	 * 
	 * @param x        posição X de referência
	 * @param y        posição Y de referência
	 * @param distance Distância para a busca
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	List<POIDocument> find(Double x, Double y, Double distance) throws POIException;

	/**
	 * Busca todos os Pontos de Interesse
	 * 
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	List<POIDocument> find() throws POIException;

	/**
	 * Busca um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return dto do documento encontrado
	 * @throws POIException
	 */
	POIDTO findDTO(POIDTO dto) throws POIException;

	/**
	 * Busca por Pontos próximos a um ponto de referência
	 * 
	 * @param x        posição X de referência
	 * @param y        posição Y de referência
	 * @param distance Distância para a busca
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	List<POIDTO> findDTO(Double x, Double y, Double distance) throws POIException;

	/**
	 * Busca todos os Pontos de Interesse
	 * 
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	List<POIDTO> findDTO() throws POIException;

	/**
	 * Apaga um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @throws POIException
	 */
	void delete(POIDTO dto) throws POIException;

	/**
	 * Apaga todos os Pontos de Interesse
	 * 
	 * @throws POIException
	 */
	void deleteAll() throws POIException;
}
