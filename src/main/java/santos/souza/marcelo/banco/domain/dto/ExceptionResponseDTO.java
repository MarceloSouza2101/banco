package santos.souza.marcelo.banco.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionResponseDTO {

	private String type;
	private String title;
	private List<String> detail;
	private String instance;
}
