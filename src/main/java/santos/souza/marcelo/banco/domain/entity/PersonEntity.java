package santos.souza.marcelo.banco.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PERSON", schema = "dbo")
public class PersonEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DATE_BIRTH")
	private LocalDate dateBirth;
	
	@JoinColumn(name = "ID_ADDRESS", referencedColumnName = "IDENT")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AddressEntity address;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, orphanRemoval = true, cascade=CascadeType.ALL)
	private List<AccountEntity> accounts;
}
