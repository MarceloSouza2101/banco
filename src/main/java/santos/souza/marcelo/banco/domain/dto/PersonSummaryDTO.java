package santos.souza.marcelo.banco.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonSummaryDTO {

	private String name;
	
	private String cpf;
	
	private String email;
}
