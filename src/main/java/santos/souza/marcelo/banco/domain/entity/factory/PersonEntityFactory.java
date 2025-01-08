package santos.souza.marcelo.banco.domain.entity.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.vo.PersonUpdateVO;
import santos.souza.marcelo.banco.domain.vo.PersonVO;

@Generated
public class PersonEntityFactory {

	private PersonEntityFactory() {}

	public static PersonEntity convertToEntity(PersonVO newPersonVO, AddressDto addressPostalCode) {

		return PersonEntity.builder()
				.name(newPersonVO.getName())
				.cpf(newPersonVO.getCpf())
				.dateBirth(newPersonVO.getDateBirth())
				.phone(newPersonVO.getPhone())
				.email(newPersonVO.getEmail())
				.address(AddressEntityFactory.convertToEntity(newPersonVO.getAddress(), addressPostalCode))
				.build();
	}

	public static void convertToEntityForUpdate(PersonEntity personEntity, PersonUpdateVO updatePersonVO, AddressDto addressPostalCode) {
		
		personEntity.setEmail(updatePersonVO.getEmail());
		personEntity.setPhone(updatePersonVO.getPhone());
		AddressEntityFactory.convertToEntityForUpdate(personEntity.getAddress(), updatePersonVO.getAddress(), addressPostalCode);
	}
	
	
}
