package santos.souza.marcelo.banco.enumerator;

public enum AccountTypeEnum {

	CHECKING_ACCOUNT("Checking Account", 10), SAVING_ACCOUNT("Savings Account", 2);
	
	private String description;
	private Integer monthlyFee;
	
	private AccountTypeEnum(String description, Integer monthlyFee) {
		this.description = description;
		this.monthlyFee = monthlyFee;

	}

	public String getDescription() {
		return this.description;
	}
	
	public Integer getMonthlyFee() {
		return this.monthlyFee;
	}
}
