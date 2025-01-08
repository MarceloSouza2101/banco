package santos.souza.marcelo.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.dto.AccountDTO;
import santos.souza.marcelo.banco.domain.dto.TransactionVO;
import santos.souza.marcelo.banco.domain.vo.AccountVO;
import santos.souza.marcelo.banco.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/{idAccount}")
	public AccountDTO getAccountnById(@PathVariable Long idAccount) {
	
		return accountService.getAccountnById(idAccount);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Long registerAccount(@RequestBody @Valid AccountVO newAccount) {
	
		return accountService.registerAccount(newAccount);
	}
	
	@PatchMapping("/transactions/{idAccount}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void makeTransaction(@PathVariable Long idAccount, @RequestBody @Valid TransactionVO newTransaction) {
	
		accountService.makeTransaction(idAccount, newTransaction);
	}
	
	@DeleteMapping("/{idAccount}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccount(@PathVariable Long idAccount) {
	
		accountService.deleteAccount(idAccount);
	}
}
