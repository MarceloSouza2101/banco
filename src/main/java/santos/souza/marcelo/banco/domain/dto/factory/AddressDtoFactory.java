package santos.souza.marcelo.banco.domain.dto.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.entity.AddressEntity;

@Generated
public class AddressDtoFactory {

	private AddressDtoFactory() {}

	public static AddressDto convertToDTO(AddressEntity address) {

		return AddressDto.builder()
				.id(address.getId())
				.complement(address.getComplement())
				.number(address.getNumber())
				.postalCode(address.getPostalCode())
				.street(address.getStreet())
				.city(address.getCity())
				.district(address.getDistrict())
				.uf(address.getUf())
				.build();
	}
	
}
