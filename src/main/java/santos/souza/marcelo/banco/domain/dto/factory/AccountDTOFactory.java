package santos.souza.marcelo.banco.domain.dto.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.AccountDTO;
import santos.souza.marcelo.banco.domain.entity.AccountEntity;

@Generated
public class AccountDTOFactory {

	AccountDTOFactory() {}

	public static AccountDTO convertToAccount(AccountEntity accountEntity) {

		return AccountDTO.builder()
				.id(accountEntity.getId())
				.accountType(accountEntity.getAccountType().getDescription())
				.balance(accountEntity.getBalance())
				.monthlyfee(accountEntity.getMonthlyfee())
				.branch(accountEntity.getBranch())
				.person(PersonSummaryDTOFactory.convertToDTO(accountEntity.getPerson()))
				.build();
	}
	
}
