package santos.souza.marcelo.banco.domain.dto.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.PersonDTO;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;

@Generated
public class PersonDTOFactory {

	private PersonDTOFactory() {}

	public static PersonDTO convertToDTO(PersonEntity person) {

		return PersonDTO.builder()
				.id(person.getId())
				.name(person.getName())
				.cpf(person.getCpf())
				.email(person.getEmail())
				.phone(person.getPhone())
				.dateBirth(person.getDateBirth())
				.address(AddressDtoFactory.convertToDTO(person.getAddress()))
				.build();
	}
}
