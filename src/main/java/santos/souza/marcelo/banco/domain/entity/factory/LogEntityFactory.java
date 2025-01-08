package santos.souza.marcelo.banco.domain.entity.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.entity.LogEntity;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;

@Generated
public class LogEntityFactory {

	private LogEntityFactory() {}

	public static LogEntity convertToEntity(String json, ConsultationTypeEnum type) {

		return LogEntity.builder()
				.type(type)
				.json(json)
				.build();
	}

}
