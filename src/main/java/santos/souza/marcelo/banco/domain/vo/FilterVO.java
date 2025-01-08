package santos.souza.marcelo.banco.domain.vo;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Builder
@Getter
public class FilterVO {

	private String name;

	private String cep;
	
	private LocalDate dateBirth;

	public boolean includeName() {
		return this.name != null;
	}

	public boolean includeCep() {
		return this.cep != null;
	}

	public boolean includeDateBirth() {
		return this.dateBirth != null;
	}
}
