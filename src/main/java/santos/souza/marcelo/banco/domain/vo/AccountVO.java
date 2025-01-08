package santos.souza.marcelo.banco.domain.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import santos.souza.marcelo.banco.enumerator.AccountTypeEnum;

@Getter
@Builder
public class AccountVO {

	@NotNull(message = "Tipo de conta é um campo obrigatório")
	private AccountTypeEnum accountType;
	
	@Min(value = 0, message = "O valor não pode ser negativo")
	private Integer balance;
	
	@NotNull(message = "Pessoa é um campo obrigatório")
	private Long idPerson;
	
	@Max(value = 9999, message = "Agência não pode ter mais de 999 caracteres")
	private Integer branch;
}
