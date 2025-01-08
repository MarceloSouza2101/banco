package santos.souza.marcelo.banco.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import santos.souza.marcelo.banco.domain.dto.AddressDto;
import santos.souza.marcelo.banco.service.client.CepClient;

@Service
@RequiredArgsConstructor
public class CepService {

	private final CepClient cepClient;

	public AddressDto findCep(String cep) {
		
		return cepClient.get(cep, AddressDto.class);
	}
}
