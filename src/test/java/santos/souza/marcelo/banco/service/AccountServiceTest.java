package santos.souza.marcelo.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import santos.souza.marcelo.banco.domain.dto.AccountDTO;
import santos.souza.marcelo.banco.domain.dto.TransactionVO;
import santos.souza.marcelo.banco.domain.entity.AccountEntity;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.vo.AccountVO;
import santos.souza.marcelo.banco.enumerator.AccountTypeEnum;
import santos.souza.marcelo.banco.enumerator.TransactionTypeEnum;
import santos.souza.marcelo.banco.exception.DadosJaCadastradosException;
import santos.souza.marcelo.banco.exception.MsgException;
import santos.souza.marcelo.banco.exception.NaoEncontradoException;
import santos.souza.marcelo.banco.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private PersonService personService;
	
	@Mock
	private LogService logService;
	
	@InjectMocks
	private AccountService accountService;
	
	@Test
	@Order(1)
	void waitToSaveAccount() {
		
		AccountVO accountVO = gerarAccount();
		
		when(accountRepository.existsByPersonIdAndAccountType(any(), any())).thenReturn(false);

		when(accountRepository.save(any())).thenReturn(AccountEntity.builder().id(1L).build());
		
		Long idAccount = accountService.registerAccount(accountVO);
		
		assertEquals(1L, idAccount);

	}
	
	@Test
	@Order(2)
	void waitNotSaveAccount() {
		
		AccountVO accountVO = gerarAccount();
		
		when(accountRepository.existsByPersonIdAndAccountType(any(), any())).thenReturn(true);

		assertThrows(DadosJaCadastradosException.class, () -> accountService.registerAccount(accountVO));

	}
	
	@Test
	@Order(3)
	void waitFindById() {
				
		AccountEntity accountEntity = generateAccountEntity();
		
		when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));

		AccountDTO accountDTO = accountService.getAccountnById(1L);
		
		assertEquals(accountDTO.getBalance(), accountEntity.getBalance());
		
	}
	
	@Test
	@Order(4)
	void waitNotFindById() {
						
		when(accountRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(NaoEncontradoException.class, () -> accountService.getAccountnById(10000L));
		
	}
	
	@Test
	@Order(5)
	void waitTransactional() {
				
		AccountEntity accountEntity = generateAccountEntity();
		
		when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));

		TransactionVO transactionVO = TransactionVO.builder()
		.value(5)
		.transactionTypeEnum(TransactionTypeEnum.DEPOSIT)
		.build();
		
		accountService.makeTransaction(1L, transactionVO);
				
	}
	
	@Test
	@Order(6)
	void waitNotTransactional() {
				
		AccountEntity accountEntity = generateAccountEntity();
		
		when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));

		TransactionVO transactionVO = TransactionVO.builder()
		.value(100)
		.transactionTypeEnum(TransactionTypeEnum.WITHDRAWAL)
		.build();
		
		assertThrows(MsgException.class, () -> accountService.makeTransaction(1L, transactionVO));
				
	}
	
	@Test
	@Order(7)
	void waitDelete() {
		
		AccountEntity accountEntity = generateAccountEntity();
				
		when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));
		
		accountService.deleteAccount(1L);
		
	}
	
	@Test
	@Order(8)
	void waitNotDelete() {
		
		AccountEntity accountEntity = generateAccountEntity();
				
		accountEntity.setBalance(5);
		
		when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));
		
		assertThrows(MsgException.class, () -> accountService.deleteAccount(1L));
		
	}

	private AccountEntity generateAccountEntity() {

		return AccountEntity.builder()
				.id(1l)
				.accountType(AccountTypeEnum.CHECKING_ACCOUNT)
				.balance(0)
				.branch(1234)
				.person(PersonEntity.builder().cpf("48454053821").name("João").email("Joao@gmail.com").build())
				.build();
	}

	private AccountVO gerarAccount() {

		return AccountVO.builder()
				.accountType(AccountTypeEnum.CHECKING_ACCOUNT)
				.balance(0)
				.branch(1234)
				.idPerson(1L)
				.build();
	}

}
