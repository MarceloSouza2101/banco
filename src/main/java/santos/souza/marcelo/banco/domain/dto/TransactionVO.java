package santos.souza.marcelo.banco.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import santos.souza.marcelo.banco.enumerator.TransactionTypeEnum;

@Getter
@Builder
public class TransactionVO {

	@NotNull(message = "Valor é um campo obrigatório")
	@Min(value = 0, message = "O valor não pode ser negativo")
	private Integer value;
	
	@NotNull(message = "Tipo de transação é um campo obrigatório")
	private TransactionTypeEnum transactionTypeEnum; 
}
