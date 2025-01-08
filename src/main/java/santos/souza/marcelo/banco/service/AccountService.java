package santos.souza.marcelo.banco.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.dto.AccountDTO;
import santos.souza.marcelo.banco.domain.dto.TransactionVO;
import santos.souza.marcelo.banco.domain.dto.factory.AccountDTOFactory;
import santos.souza.marcelo.banco.domain.entity.AccountEntity;
import santos.souza.marcelo.banco.domain.entity.factory.AccountEntityFactory;
import santos.souza.marcelo.banco.domain.vo.AccountVO;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;
import santos.souza.marcelo.banco.enumerator.TransactionTypeEnum;
import santos.souza.marcelo.banco.exception.DadosJaCadastradosException;
import santos.souza.marcelo.banco.exception.MsgException;
import santos.souza.marcelo.banco.exception.NaoEncontradoException;
import santos.souza.marcelo.banco.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	
	private final PersonService personService;
	
	private final LogService logService;
	
	public AccountDTO getAccountnById(Long idAccount) {

		AccountEntity accountEntity = findByAccount(idAccount);
		
		AccountDTO accountDTO = AccountDTOFactory.convertToAccount(accountEntity);
		
		logService.registerLog(accountDTO, ConsultationTypeEnum.ACCOUNT_ID);
		
		return accountDTO;
	}
	
	@Transactional
	public Long registerAccount(AccountVO newAccount) {

		AccountEntity accountEntity = AccountEntityFactory.convertToEntity(newAccount);
		
		validateData(accountEntity);
		
		accountEntity = accountRepository.save(accountEntity);
		
		return accountEntity.getId();
	}
	
	@Transactional
	public void makeTransaction(Long idAccount, TransactionVO newTransaction) {

		AccountEntity accountEntity = findByAccount(idAccount);

		validateData(newTransaction, accountEntity);
		
		AccountEntityFactory.convertToEntity(accountEntity, newTransaction);
		
	}
	
	@Transactional
	public void deleteAccount(Long idAccount) {

		AccountEntity accountEntity = findByAccount(idAccount);

		if(accountEntity.getBalance() != 0) {
			throw new MsgException("Só é possível excluir uma conta com saldo zerado");
		}
		
		accountRepository.delete(accountEntity);
	}

	private void validateData(AccountEntity accountEntity) {

		personService.existsByPerson(accountEntity.getPerson().getId());
		
		if(accountRepository.existsByPersonIdAndAccountType(accountEntity.getPerson().getId(), accountEntity.getAccountType())) {
			throw new DadosJaCadastradosException("");
		}
	}
	
	private void validateData(TransactionVO newTransaction, AccountEntity accountEntity) {

		if(newTransaction.getTransactionTypeEnum().equals(TransactionTypeEnum.WITHDRAWAL) && (accountEntity.getBalance() - newTransaction.getValue()) < 0) {
			throw new MsgException("Não tem saldo suficiente para realizar essa operação");
		}
		
	}
	
	private AccountEntity findByAccount(Long idAccount) {

		return accountRepository.findById(idAccount).orElseThrow(() -> new NaoEncontradoException("Nenhuma conta encontrada"));
	}

}
