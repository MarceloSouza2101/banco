package santos.souza.marcelo.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.dto.PersonDTO;
import santos.souza.marcelo.banco.domain.dto.PersonSummaryDTO;
import santos.souza.marcelo.banco.domain.entity.AddressEntity;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.vo.AddressVO;
import santos.souza.marcelo.banco.domain.vo.FilterVO;
import santos.souza.marcelo.banco.domain.vo.PersonUpdateVO;
import santos.souza.marcelo.banco.domain.vo.PersonVO;
import santos.souza.marcelo.banco.exception.DadosJaCadastradosException;
import santos.souza.marcelo.banco.exception.NaoEncontradoException;
import santos.souza.marcelo.banco.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private LogService logService;
	
	@Mock
	private CepService cepService;
	
	@InjectMocks
    private PersonService personService;
	
	@Test
	@Order(1)
	void waitToSavePerson() {
		
		PersonVO personVO = generatePerson();
		
		AddressDto endereco = generateAddress();
		
		when(cepService.findCep(personVO.getAddress().getPostalCode())).thenReturn(endereco);

		when(personRepository.existsByCpfOrEmail(personVO.getCpf(), personVO.getEmail())).thenReturn(false);
		
		when(personRepository.save(any())).thenReturn(PersonEntity.builder().id(1L).build());

		Long idPerson = personService.registerPerson(personVO);
		
		assertEquals(1L, idPerson);

	}
	
	@Test
	@Order(2)
	void waitForAlreadyRegisteredData() {
		
		PersonVO personVO = generatePerson();
		
		AddressDto endereco = generateAddress();
		
		when(cepService.findCep(personVO.getAddress().getPostalCode())).thenReturn(endereco);

		when(personRepository.existsByCpfOrEmail(personVO.getCpf(), personVO.getEmail())).thenReturn(true);
		
		assertThrows(DadosJaCadastradosException.class, () -> personService.registerPerson(personVO));

	}
	
	@Test
	@Order(3)
	void waitUpdate() {
		
		PersonUpdateVO personVO = generatePersonUpdate();
				
		AddressDto endereco = generateAddress();
		
		PersonEntity personEntity = generatePersonEntity();
		
		when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));
		
		when(cepService.findCep(personVO.getAddress().getPostalCode())).thenReturn(endereco);

		when(personRepository.save(any())).thenReturn(PersonEntity.builder().id(1L).build());

		Long idPerson = personService.updatePerson(1L, personVO);
		
		assertEquals(1L, idPerson);		

	}
	
	@Test
	@Order(4)
	void waitNotUpdate() {
		
		PersonUpdateVO personVO = generatePersonUpdate();
		
		when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThrows(NaoEncontradoException.class, () -> personService.updatePerson(1L, personVO));	

	}
	
	@Test
	@Order(5)
	void waitFindById() {
		
		PersonEntity personEntity = generatePersonEntity();
		
		PersonDTO personDTO = generatePersonDTO();
		
		when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));
		
		PersonDTO personDTOResponse = personService.getPersonById(1L);
		
		assertEquals(personDTO.getCpf(), personDTOResponse.getCpf());
	}
	
	@Test
	@Order(6)
	void waitFindByFilters() {
		
		PersonEntity personEntity = generatePersonEntity();
		
		FilterVO filterVO = FilterVO.builder()
		.cep("12345678")
		.dateBirth(LocalDate.of(2000, 01, 21))
		.name("João")
		.build();
		
		Page<PersonEntity> page = new PageImpl<>(List.of(personEntity));
		
		when(personRepository.findAll(any(Specification.class), any(Pageable.class)))
	    .thenReturn(page);		
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<PersonSummaryDTO> personByFilters = personService.getPersonByFilters(pageable, filterVO);
		
		
		assertEquals(1l , personByFilters.getSize());
	}
	
	@Test
	@Order(7)
	void waitDelete() {
		
		PersonEntity personEntity = generatePersonEntity();
				
		when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));
		
		personService.deletePerson(1L);
		
	}
	
	@Test
	@Order(8)
	void waitExists() {
			
		when(personRepository.existsById(anyLong())).thenReturn(true);
		
		personService.existsByPerson(1L);
		
	}
	
	@Test
	@Order(7)
	void waitNotExists() {
		
		when(personRepository.existsById(anyLong())).thenReturn(false);
				
		assertThrows(NaoEncontradoException.class, () -> personService.existsByPerson(10000L));	

		
	}

	private PersonDTO generatePersonDTO() {

		return PersonDTO.builder()
                .id(1L)
                .name("João Silva")
                .cpf("68767711712")
                .phone("11987654321")
                .email("joao.SouzaSilva@email.com")
                .dateBirth(LocalDate.of(1990, 5, 15))
                .address(AddressDto.builder()
                        .id(1L)
                        .street("Praça da Sé")
                        .complement("Apartamento 101")
                        .postalCode("12345678")
                        .district("Sé")
                        .city("São Paulo")
                        .uf("SP")
                        .number(123)
                        .build())
                .build();
	}

	private PersonEntity generatePersonEntity() {
		return PersonEntity.builder()
                .id(1L)
                .name("João Silva")
                .cpf("68767711712")
                .phone("11987654321")
                .email("joao.SouzaSilva@email.com")
                .dateBirth(LocalDate.of(2000, 01, 21))
                .address(AddressEntity.builder()
                        .id(1L)
                        .street("Praça da Sé")
                        .complement("Apartamento 101")
                        .postalCode("12345678")
                        .district("Sé")
                        .city("São Paulo")
                        .uf("SP")
                        .number(123)
                        .build())
                .build();
	}

	private PersonUpdateVO generatePersonUpdate() {

		return PersonUpdateVO.builder()
			    .phone("11987654321")
			    .email("joaoSilva.silva@email.com")
			    .address(AddressVO.builder()
			        .number(123)
			        .postalCode("12345678")
			        .complement("Apartamento 101")
			        .build())
			    .build();
	}

	private AddressDto generateAddress() {
		return AddressDto.builder()
			    .postalCode("12345678")
			    .street("Praça da Sé")
			    .district("Sé")
			    .city("São Paulo")
			    .uf("SP")
			    .build();
	}

	private PersonVO generatePerson() {

		return PersonVO.builder()
			    .name("João Silva")
			    .cpf("68767711712")
			    .phone("11987654321")
			    .dateBirth(LocalDate.of(2000, 01, 21))
			    .email("joao.silva@email.com")
			    .address(AddressVO.builder()
			        .number(123)
			        .postalCode("12345678")
			        .complement("Apartamento 101")
			        .build())
			    .build();
	}

}
