package santos.souza.marcelo.banco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import santos.souza.marcelo.banco.domain.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

	@Query("SELECT CASE WHEN COUNT(person) > 0 THEN TRUE ELSE FALSE END FROM PersonEntity person WHERE person.cpf = :cpf OR person.email = :email")
    boolean existsByCpfOrEmail(String cpf, String email);

	Page<PersonEntity> findAll(Specification<PersonEntity> specs, Pageable pegeable);
}
