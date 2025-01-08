package santos.souza.marcelo.banco.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import santos.souza.marcelo.banco.service.client.CepClient;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

	@Mock
	private CepClient cepClient;

	@InjectMocks
	private CepService cepService;
	
	@Test
	void waitFindCep() {

		cepService.findCep("12345678");
	}

}
