package santos.souza.marcelo.banco.domain.vo;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonVO {

	@NotBlank(message = "Nome é um campo obrigatório")
	@Size(max = 150, message = "Nome não pode ter mais 150 caracteres")
	private String name;
		
	@NotBlank(message = "CPF é um campo obrigatório")
	@Pattern(regexp = "^(([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2}))$", message = "CPF informado é inválido")
	@Size(max = 11, min = 11, message = "Tamanho do CPF inválido")
	private String cpf;
	
	@Size(max = 11, message = "Telefone não pode ter mais de 11 caracteres")
	private String phone;

	@NotNull(message = "Data de Nascimento é um campo obrigatório")
	@PastOrPresent(message = "Não é possível cadastrar Data de Nascimento com data futura")
	private LocalDate dateBirth;

	@NotBlank(message = "E-mail é um campo obrigatório")
	@Email(message = "Formato de e-mail inválido")
	@Size(max = 40, message = "E-mail não pode ter mais de 40 caracteres")
	private String email;
	
	@Valid
	@NotNull(message = "Necessário o preenchimento do endereço")
	private AddressVO address;
	
}
