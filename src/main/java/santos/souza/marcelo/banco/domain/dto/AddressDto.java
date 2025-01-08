package santos.souza.marcelo.banco.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDto {

	private Long id;
	
	private String street;
	
	private String complement;
	
	private String postalCode;
	
	private String district;

	private String city;

	private String uf;
	
	private Integer number;
}
