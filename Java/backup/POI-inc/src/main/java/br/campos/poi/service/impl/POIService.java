package br.campos.poi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.campos.poi.model.document.POIDocument;
import br.campos.poi.model.factory.AbstractDataFactory;
import br.campos.poi.model.factory.DocumentFactory;
import br.campos.poi.model.factory.ObjectDTOFactory;
import br.campos.poi.model.transfer.POIDTO;
import br.campos.poi.repository.POIMongoRepository;
import br.campos.poi.service.abst.IPOIService;
import br.campos.poi.util.POIException;

/**
 * Serviço de Pontos de Interesse
 * 
 * @author Caio
 */
@Service
public class POIService implements IPOIService {

	/**
	 * Instância de Repositório de Pontos de Interesse
	 */
	@Autowired
	POIMongoRepository poiMongoRepository;

	/**
	 * Cria um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento gerado
	 * @throws POIException
	 */
	public POIDocument create(POIDTO dto) throws POIException {
		try {
			POIDocument poi = this.parse(dto);
			this.validate(poi);
			return poiMongoRepository.save(poi);
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível salvar os dados", e);
		}
	}

	/**
	 * Atualiza um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento gerado
	 * @throws POIException
	 */
	public POIDocument update(POIDTO dto) throws POIException {
		try {
			POIDocument poi = this.find(dto);
			poi.setLocation(dto.getX(), dto.getY());
			this.validate(poi);
			return poiMongoRepository.save(poi);
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível salvar os dados", e);
		}
	}

	/**
	 * Busca um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return documento encontrado
	 * @throws POIException
	 */
	public POIDocument find(POIDTO dto) throws POIException {
		try {
			return poiMongoRepository.findByName(dto.getName());
		} catch (Exception e) {
			throw new POIException("Não foi possível encontrar o registro", e);
		}
	}

	/**
	 * Busca por Pontos próximos a um ponto de referência
	 * 
	 * @param x        posição X de referência
	 * @param y        posição Y de referência
	 * @param distance Distância para a busca
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	public List<POIDocument> find(Double x, Double y, Double distance) throws POIException {
		try {
			return poiMongoRepository.findByLocationDistance(x, y, distance);
		} catch (Exception e) {
			throw new POIException("Não foi possível encontrar os registros", e);
		}
	}

	/**
	 * Busca todos os Pontos de Interesse
	 * 
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	public List<POIDocument> find() throws POIException {
		try {
			return poiMongoRepository.findAll();
		} catch (Exception e) {
			throw new POIException("Não foi possível encontrar os registros", e);
		}
	}

	/**
	 * Busca um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @return dto do documento encontrado
	 * @throws POIException
	 */
	public POIDTO findDTO(POIDTO dto) throws POIException {
		try {
			POIDocument poi = this.find(dto);
			return this.parse(poi);
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível encontrar o registro", e);
		}
	}

	/**
	 * Busca por Pontos próximos a um ponto de referência
	 * 
	 * @param x        posição X de referência
	 * @param y        posição Y de referência
	 * @param distance Distância para a busca
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	public List<POIDTO> findDTO(Double x, Double y, Double distance) throws POIException {
		try {
			List<POIDocument> list = this.find(x, y, distance);
			return parseAll(list);
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível encontrar os registros", e);
		}
	}

	/**
	 * Busca todos os Pontos de Interesse
	 * 
	 * @return lista de Pontos de Interesse
	 * @throws POIException
	 */
	public List<POIDTO> findDTO() throws POIException {
		try {
			List<POIDocument> list = this.find();
			return parseAll(list);
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível encontrar os registros", e);
		}
	}

	/**
	 * Apaga um Ponto de Interesse
	 * 
	 * @param dto Objeto de referência
	 * @throws POIException
	 */
	public void delete(POIDTO dto) throws POIException {
		try {
			POIDocument poi = this.find(dto);
			poiMongoRepository.deleteById(poi.getId());
		} catch (Exception e) {
			if (e instanceof POIException) {
				throw e;
			}
			throw new POIException("Não foi possível apagar o registro", e);
		}
	}

	/**
	 * Apaga todos os Pontos de Interesse
	 * 
	 * @throws POIException
	 */
	public void deleteAll() throws POIException {
		try {
			poiMongoRepository.deleteAll();
		} catch (Exception e) {
			throw new POIException("Não foi possível apagar os registros", e);
		}
	}

	/**
	 * Converte formatos de Ponto de Interesse
	 * 
	 * @param dto Ponto de Interesse no formato DTO
	 * @return Ponto de Interesse no formato Documento
	 * @throws POIException
	 */
	private POIDocument parse(POIDTO dto) throws POIException {
		try {
			DocumentFactory factory = AbstractDataFactory.getFactory(dto);
			return (POIDocument) factory.create(dto);
		} catch (Exception e) {
			throw new POIException("Não foi possível converter o tipo de dados", e);
		}
	}

	/**
	 * Converte formatos de Ponto de Interesse
	 * 
	 * @param dto Ponto de Interesse no formato Documento
	 * @return Ponto de Interesse no formato DTO
	 * @throws POIException
	 */
	private POIDTO parse(POIDocument doc) throws POIException {
		try {
			ObjectDTOFactory factory = AbstractDataFactory.getFactory(doc);
			return (POIDTO) factory.create(doc);
		} catch (Exception e) {
			throw new POIException("Não foi possível converter o tipo de dados", e);
		}
	}

	/**
	 * Converte formatos de Pontos de Interesse
	 * 
	 * @param list lista de Pontos de Interesse no formato Documento
	 * @return lista de Pontos de Interesse no formato DTO
	 * @throws POIException
	 */
	private List<POIDTO> parseAll(List<POIDocument> list) throws POIException {
		try {
			return list.parallelStream().map(doc -> {
				try {
					return this.parse(doc);
				} catch (Exception e) {
					return null;
				}
			}).filter(dto -> dto != null).collect(Collectors.toList());
		} catch (Exception e) {
			throw new POIException("Não foi possível converter o tipo de dados", e);
		}
	}

	/**
	 * Valida um Pontos de Interesse
	 * 
	 * @param poi documento de Pontos de Interesse
	 * @throws POIException
	 */
	private void validate(POIDocument poi) throws POIException {
		if (poi.getName() == null || poi.getName().isEmpty()) {
			throw new POIException("O nome não pode estar vazio");
		} else if (poi.getX().compareTo(0D) == -1) {
			throw new POIException("Valor de X não pode ser negativo");
		} else if (poi.getY().compareTo(0D) == -1) {
			throw new POIException("Valor de Y não pode ser negativo");
		}
	}

}
