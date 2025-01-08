package santos.souza.marcelo.banco.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.domain.dto.PersonDTO;
import santos.souza.marcelo.banco.domain.entity.LogEntity;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;
import santos.souza.marcelo.banco.exception.MsgException;
import santos.souza.marcelo.banco.repository.LogRepository;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LogService logService;

	@Test
	@Order(1)
	void waitSave() {

		PersonDTO personDTO = generatePersonDTO();

		when(logRepository.save(any())).thenReturn(LogEntity.builder().id(1l).build());

		logService.registerLog(personDTO, ConsultationTypeEnum.PERSON_ID);

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
}
