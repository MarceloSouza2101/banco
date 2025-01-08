package santos.souza.marcelo.banco.domain.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonUpdateVO {
		
	@Size(max = 11, message = "Telefone não pode ter mais de 11 caracteres")
	private String phone;

	@NotBlank(message = "E-mail é um campo obrigatório")
	@Email(message = "Formato de e-mail inválido")
	@Size(max = 40, message = "E-mail não pode ter mais de 40 caracteres")
	private String email;
	
	@Valid
	@NotNull(message = "Endereço é um campo obrigatório")
	private AddressVO address;
	
}
