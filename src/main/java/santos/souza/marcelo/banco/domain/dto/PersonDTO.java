package santos.souza.marcelo.banco.domain.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonDTO {

	
	private Long id;
	
	private String name;
	
	private String cpf;
	
	private String phone;
	
	private String email;
	
	private LocalDate dateBirth;
	
	private AddressDto address;	
}
