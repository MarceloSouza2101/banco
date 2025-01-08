package santos.souza.marcelo.banco.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "TB_ADDRESS", schema = "dbo")
public class AddressEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;

	@Column(name = "STREET")
	private String street;
	
	@Column(name = "COMPLEMENT")
	private String complement;
	
	@Column(name = "POSTAL_CODE")
	private String postalCode;
	
	@Column(name = "NUMBER")
	private Integer number;
	
	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "CITY")
	private String city;
	
	@Column(name = "UF")
	private String uf;
	
}
