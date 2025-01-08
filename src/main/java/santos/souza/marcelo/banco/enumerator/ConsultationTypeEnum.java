package santos.souza.marcelo.banco.enumerator;

public enum ConsultationTypeEnum {

	PERSON_ID("/banks/person"), PERSON_FILTER("/banks/person/filters"), ACCOUNT_ID("/banks/accounts");
	
	private String description;
	
	private ConsultationTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
