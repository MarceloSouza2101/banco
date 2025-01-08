package santos.souza.marcelo.banco.domain.entity;

import static santos.souza.marcelo.banco.util.Constante.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import santos.souza.marcelo.banco.enumerator.ConsultationTypeEnum;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_LOG", schema = "dbo")
public class LogEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;

	@Column(name = "JSON", length = 500)
	private String json;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private ConsultationTypeEnum type;
	
	@Column(name = "DATE_CREATED", nullable = false)
    private ZonedDateTime dateCreated;

	@PrePersist
	private void onCreate() {
		dateCreated = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}
}
