package santos.souza.marcelo.banco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.dto.PersonDTO;
import santos.souza.marcelo.banco.domain.dto.PersonSummaryDTO;
import santos.souza.marcelo.banco.domain.dto.factory.PersonDTOFactory;
import santos.souza.marcelo.banco.domain.dto.factory.PersonSummaryDTOFactory;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.entity.factory.PersonEntityFactory;
import santos.souza.marcelo.banco.domain.vo.FilterVO;
import santos.souza.marcelo.banco.domain.vo.PersonUpdateVO;
import santos.souza.marcelo.banco.domain.vo.PersonVO;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;
import santos.souza.marcelo.banco.exception.DadosJaCadastradosException;
import santos.souza.marcelo.banco.exception.NaoEncontradoException;
import santos.souza.marcelo.banco.repository.PersonRepository;
import santos.souza.marcelo.banco.specification.PersonSpecification;

@Service
@RequiredArgsConstructor
public class PersonService {
	
	private final PersonRepository personRepository;
	
	private final LogService logService;
	
	private final CepService cepService;
	
	public Page<PersonSummaryDTO> getPersonByFilters(Pageable pegeable, FilterVO filters) {

		Page<PersonEntity> personFilters = personRepository.findAll(new PersonSpecification(filters), pegeable);
		
		Page<PersonSummaryDTO> page = PersonSummaryDTOFactory.convertToDTO(personFilters);
		
		logService.registerLog(page, ConsultationTypeEnum.PERSON_FILTER);

		return page;
	}
	
	public PersonDTO getPersonById(Long idPerson) {

		PersonEntity person = findByPerson(idPerson);
		
		PersonDTO personDTO = PersonDTOFactory.convertToDTO(person);
		
		logService.registerLog(personDTO, ConsultationTypeEnum.PERSON_ID);
		
		return personDTO;
	}

	@Transactional
	public Long registerPerson(PersonVO newPersonVO) {

		AddressDto cepDTO = cepService.findCep(newPersonVO.getAddress().getPostalCode());
		
		PersonEntity personEntity = PersonEntityFactory.convertToEntity(newPersonVO, cepDTO);
		
		validateData(personEntity);		
		
		PersonEntity personSave = personRepository.save(personEntity);
				
		return personSave.getId();
	}

	@Transactional
	public Long updatePerson(Long idPerson, PersonUpdateVO updatePersonVO) {

		PersonEntity personEntity = findByPerson(idPerson);

		AddressDto cepDTO = cepService.findCep(updatePersonVO.getAddress().getPostalCode());
		
		PersonEntityFactory.convertToEntityForUpdate(personEntity, updatePersonVO, cepDTO);
		
		personRepository.save(personEntity);
		
		return personEntity.getId();
	}
	
	@Transactional
	public void deletePerson(Long idPerson) {

		PersonEntity personEntity = findByPerson(idPerson);
		
		personRepository.delete(personEntity);
	}
	
	private void validateData(PersonEntity personEntity) {

		if(personRepository.existsByCpfOrEmail(personEntity.getCpf(), personEntity.getEmail())){
			throw new DadosJaCadastradosException("CPF ou Email já cadastrado.");
		}		
	}

	private PersonEntity findByPerson(Long idPerson) {

		return personRepository.findById(idPerson).orElseThrow(() -> new NaoEncontradoException("Nenhuma pessoa encontrada"));
	}
	
	protected void existsByPerson(Long idPerson) {

		if(!personRepository.existsById(idPerson)) {
			throw new NaoEncontradoException("Nenhuma pessoa encontrada");
		}
	}

}
