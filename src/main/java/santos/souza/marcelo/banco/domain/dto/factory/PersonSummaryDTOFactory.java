package santos.souza.marcelo.banco.domain.dto.factory;

import org.springframework.data.domain.Page;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.PersonSummaryDTO;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;

@Generated
public class PersonSummaryDTOFactory {

	private PersonSummaryDTOFactory() {}

	public static PersonSummaryDTO convertToDTO(PersonEntity person) {

		return PersonSummaryDTO.builder()
				.name(person.getName())
				.email(person.getEmail())
				.cpf(person.getCpf())
				.build();
	}
	
	public static Page<PersonSummaryDTO> convertToDTO(Page<PersonEntity> personFilters) {

		return personFilters.map(PersonSummaryDTOFactory::convertToDTO);
	}
	
}
