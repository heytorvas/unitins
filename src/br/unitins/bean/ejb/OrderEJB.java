package br.unitins.bean.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unitins.model.Customer;
import br.unitins.model.OrderDB;
import br.unitins.model.Payment;
import br.unitins.model.PaymentType;

@Stateful
public class OrderEJB {
	@PersistenceContext
	private EntityManager em;

	public void insert(OrderDB order, Integer idCustomer, Integer idPayment) {
		order.setCustomer(em.find(Customer.class, idCustomer));
		order.setPayment(em.find(Payment.class, idPayment));
		em.persist(order);
	}

	public void update(OrderDB order) {
		em.merge(order);
	}

	public void delete(OrderDB order) {
		order = load(order.getId());
		em.remove(order);
	}

	public OrderDB load(Integer id) {
		return em.find(OrderDB.class, id);
	}

	public List<OrderDB> findAll() {
		return em.createQuery("select o from OrderDB o", OrderDB.class).getResultList();
	}
}
