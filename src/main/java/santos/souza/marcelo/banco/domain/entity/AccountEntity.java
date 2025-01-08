package santos.souza.marcelo.banco.domain.entity;

import static santos.souza.marcelo.banco.util.Constante.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import santos.souza.marcelo.banco.enumerator.AccountTypeEnum;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ACCOUNT", schema = "dbo")
public class AccountEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACCOUNT_TYPE")
	private AccountTypeEnum accountType;
	
	@Column(name = "BALANCE")
	private Integer balance;
	
	@Column(name = "MONTHLY_FREE")
	private Integer monthlyfee;
	
	@Column(name = "BRANCH")
	private Integer branch;
	
	@Column(name = "DATE_CREATED")
    private ZonedDateTime dateCreated;

	@JoinColumn(name = "ID_PERSON", referencedColumnName = "IDENT")
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
	
	@PrePersist
	private void onCreate() {
		dateCreated = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}
}
