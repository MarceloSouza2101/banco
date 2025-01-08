package santos.souza.marcelo.banco.domain.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressVO {
	
	@NotNull(message = "Número é um campo obrigatório")
	private Integer number;
	
	@NotNull(message = "CEP é um campo obrigatório")
	@Size(max = 8, message = "CEP não pode ter mais de 8 caracteres")
	private String postalCode;
	
	private String complement;
	
}
