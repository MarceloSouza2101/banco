package santos.souza.marcelo.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import santos.souza.marcelo.banco.domain.entity.AccountEntity;
import santos.souza.marcelo.banco.enumerator.AccountTypeEnum;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

	boolean existsByPersonIdAndAccountType(Long id, AccountTypeEnum accountType);

}
