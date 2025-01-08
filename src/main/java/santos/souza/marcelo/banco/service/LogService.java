package santos.souza.marcelo.banco.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.entity.LogEntity;
import santos.souza.marcelo.banco.domain.entity.factory.LogEntityFactory;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;
import santos.souza.marcelo.banco.exception.MsgException;
import santos.souza.marcelo.banco.repository.LogRepository;

@Service
@RequiredArgsConstructor
public class LogService {

	private final LogRepository logRepository;

	public void registerLog(Object personDTO, ConsultationTypeEnum type) {
	     
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JavaTimeModule());

        // Desabilitar a formatação de datas como timestamps
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Converta o objeto Page em JSON
        try {
			String jsonString = objectMapper.writeValueAsString(personDTO);
			
			LogEntity logEntity = LogEntityFactory.convertToEntity(jsonString, type);
		     
		    logRepository.save(logEntity);
		} catch (JsonProcessingException e) {
			throw new MsgException("Erro inesperado");
		}
	}
}
