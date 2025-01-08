package santos.souza.marcelo.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import santos.souza.marcelo.banco.domain.entity.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>{

}
