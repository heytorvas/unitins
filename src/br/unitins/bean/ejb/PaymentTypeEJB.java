package br.unitins.bean.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unitins.model.PaymentType;

@Stateful
public class PaymentTypeEJB {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(PaymentType paymentType) {
		em.persist(paymentType);
	}
	
	public void update(PaymentType paymentType) {
		em.merge(paymentType);
	}
	
	public void delete(PaymentType paymentType) {
		paymentType = load(paymentType.getId());
		em.remove(paymentType);
	}
	
	public PaymentType load(Integer id) {
		return em.find(PaymentType.class, id);
	}

	public List<PaymentType> findAll() {
		return em.createQuery("select pt from PaymentType pt", PaymentType.class).getResultList();
}
}
