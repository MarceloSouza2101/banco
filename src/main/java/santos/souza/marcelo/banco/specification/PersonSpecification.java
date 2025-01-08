package santos.souza.marcelo.banco.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Generated;
import santos.souza.marcelo.banco.domain.entity.PersonEntity;
import santos.souza.marcelo.banco.domain.vo.FilterVO;

@Generated
public class PersonSpecification implements Specification<PersonEntity> {

	private static final long serialVersionUID = 1L;

	private transient FilterVO filters;

	public PersonSpecification(FilterVO filters) {
		this.filters = filters;
	}
	
	@Override
	public Predicate toPredicate(Root<PersonEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> condicoes = new ArrayList<>();

		if (filters.includeName()) {
			Predicate nome = criteriaBuilder.like(root.get("name"), filters.getName() + "%");
			condicoes.add(nome);
		}

		if (filters.includeCep()) {
			Predicate nome = criteriaBuilder.equal(root.get("address").get("cep"), filters.getCep());
			condicoes.add(nome);
		}
		
		if (filters.includeDateBirth()) {
			Predicate nome = criteriaBuilder.equal(root.get("dateBirth").get("id"), filters.getDateBirth());
			condicoes.add(nome);
		}
		
		return criteriaBuilder.and(condicoes.toArray(new Predicate[0]));
	}

}
