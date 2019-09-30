package br.unitins.bean.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unitins.model.Customer;

@Stateful
public class CustomerEJB {
	@PersistenceContext
	private EntityManager em;

	public void insert(Customer customer) {
		em.persist(customer);
	}

	public void update(Customer customer) {
		em.merge(customer);
	}

	public void delete(Customer customer) {
		customer = load(customer.getId());
		em.remove(customer);
	}

	public Customer load(Integer id) {
		return em.find(Customer.class, id);
	}

	public List<Customer> findAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
}
}
