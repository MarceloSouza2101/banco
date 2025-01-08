package santos.souza.marcelo.banco.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.dto.PersonDTO;
import santos.souza.marcelo.banco.domain.dto.PersonSummaryDTO;
import santos.souza.marcelo.banco.domain.vo.FilterVO;
import santos.souza.marcelo.banco.domain.vo.PersonUpdateVO;
import santos.souza.marcelo.banco.domain.vo.PersonVO;
import santos.souza.marcelo.banco.service.PersonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

	private final PersonService personService;

	@GetMapping("/filters")
	public Page<PersonSummaryDTO> getPersonByFilters(@PageableDefault(size = 20) Pageable pegeable,
			FilterVO filters) {
	
		return personService.getPersonByFilters(pegeable, filters);
	}
	
	@GetMapping("/{idPerson}")
	public PersonDTO getPersonById(@PathVariable Long idPerson) {
	
		return personService.getPersonById(idPerson);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Long registerPerson(@RequestBody @Valid PersonVO newPerson) {
	
		return personService.registerPerson(newPerson);
	}
	
	@PutMapping("/{idPerson}")
	public Long updatePerson(@PathVariable Long idPerson, @RequestBody @Valid PersonUpdateVO newPerson) {
	
		return personService.updatePerson(idPerson, newPerson);
	}
	
	@DeleteMapping("/{idPerson}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long idPerson) {
	
		personService.deletePerson(idPerson);
	}
}
