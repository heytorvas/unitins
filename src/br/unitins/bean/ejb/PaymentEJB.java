package br.unitins.bean.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unitins.model.Payment;

@Stateful
public class PaymentEJB {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Payment payment) {
		em.persist(payment);
	}

	public void update(Payment payment) {
		em.merge(payment);
	}

	public void delete(Payment payment) {
		payment = load(payment.getId());
		em.remove(payment);
	}

	public Payment load(Integer id) {
		return em.find(Payment.class, id);
	}

	public List<Payment> findAll() {
		return em.createQuery("select pay from Payment pay", Payment.class).getResultList();
	}

}
