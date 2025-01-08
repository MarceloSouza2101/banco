package santos.souza.marcelo.banco.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDTO {

	private Long id;
	
	private String accountType;
	
	private Integer monthlyfee;
	
	private Integer branch;
	
	private Integer balance;
	
	private PersonSummaryDTO person;
}
