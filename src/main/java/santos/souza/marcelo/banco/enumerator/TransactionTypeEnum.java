package santos.souza.marcelo.banco.enumerator;

public enum TransactionTypeEnum {

	WITHDRAWAL("Withdrawal", -1), DEPOSIT("Deposit", 1);
	
	private String description;
	private Integer operation;
	
	private TransactionTypeEnum(String description, Integer operation) {
		this.description = description;
		this.operation = operation;

	}

	public String getDescription() {
		return this.description;
	}
	
	public Integer getOperation() {
		return this.operation;
	}
}
