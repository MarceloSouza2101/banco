package santos.souza.marcelo.banco.domain.entity.factory;

import lombok.Generated;
import santos.souza.marcelo.banco.domain.dto.TransactionVO;
import santos.souza.marcelo.banco.domain.entity.AccountEntity;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.vo.AccountVO;

@Generated
public class AccountEntityFactory {

	AccountEntityFactory() {}

	public static AccountEntity convertToEntity(AccountVO newAccount) {

		return AccountEntity.builder()
				.accountType(newAccount.getAccountType())
				.monthlyfee(newAccount.getAccountType().getMonthlyFee())
				.person(PersonEntity.builder().id(newAccount.getIdPerson()).build())
				.balance(newAccount.getBalance())
				.branch(newAccount.getBranch())
				.build();
	}

	public static void convertToEntity(AccountEntity accountEntity, TransactionVO newTransaction) {

		accountEntity.setBalance(accountEntity.getBalance() + (newTransaction.getValue() * newTransaction.getTransactionTypeEnum().getOperation()));
	}
}
