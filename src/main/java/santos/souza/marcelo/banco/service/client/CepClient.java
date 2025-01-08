package santos.souza.marcelo.banco.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import santos.souza.marcelo.banco.exception.NaoEncontradoException;

@Service
@RequiredArgsConstructor
@Generated
public class CepClient {
	
	@Value("${cep.wiremock}")
	private String cepUrl;
	
	private WebClient client;
	
	protected WebClient getWebClient(String url) {
	    final ExchangeStrategies strategies = ExchangeStrategies.builder()
	        .build();
        return WebClient.builder()
        		.exchangeStrategies(strategies)
        		.baseUrl(url)
        		.defaultHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE)
        		.build();
    }
	
    public <T> T get(String url, Class<T> responseClass) {
        
        this.client = this.getWebClient(this.cepUrl+url);
        
        return client.get()  
                .retrieve()
                .onStatus(
                        statusCode -> statusCode.equals(HttpStatus.NOT_FOUND), // Usando statusCode.equals() corretamente
                        response -> Mono.error(new NaoEncontradoException("CEP não encontrado"))
                    )
                .bodyToMono(responseClass)
                .block();
    }

}