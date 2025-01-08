package santos.souza.marcelo.banco.domain.entity.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.entity.AddressEntity;
import santos.souza.marcelo.banco.domain.vo.AddressVO;

@Generated
public class AddressEntityFactory {

	private AddressEntityFactory() {}

	public static AddressEntity convertToEntity(AddressVO enderecoVO, AddressDto addressPostalCode) {

		return AddressEntity.builder()
				.number(enderecoVO.getNumber())
				.postalCode(addressPostalCode.getPostalCode())
				.street(addressPostalCode.getStreet())
				.complement(addressPostalCode.getComplement())
				.city(addressPostalCode.getCity())
				.district(addressPostalCode.getDistrict())
				.uf(addressPostalCode.getUf())
				.build();
	}

	public static void convertToEntityForUpdate(AddressEntity addressEntity, AddressVO updateAddressVO, AddressDto addressPostalCode) {

		addressEntity.setNumber(updateAddressVO.getNumber());
		addressEntity.setPostalCode(addressPostalCode.getPostalCode());
		addressEntity.setStreet(addressPostalCode.getStreet());
		addressEntity.setComplement(addressPostalCode.getComplement());
		addressEntity.setCity(addressPostalCode.getCity());
		addressEntity.setDistrict(addressPostalCode.getDistrict());
		addressEntity.setUf(addressPostalCode.getUf());
		
	}
}
