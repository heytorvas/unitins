package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.bean.ejb.PaymentEJB;
import br.unitins.model.Payment;

@Named
@RequestScoped
public class PaymentBean {
	
	@EJB
	private PaymentEJB paymentEJB;

	private Payment payment;

	private Integer idSearch;

	private List<Payment> payments;
	
	
	@PostConstruct
	public void init() {
		payments = paymentEJB.findAll();
	}

	public String insert() {
		paymentEJB.insert(payment);
		return null;
	}

	public String update() {
		paymentEJB.update(payment);
		return null;
	}

	public String delete() {
		paymentEJB.delete(payment);
		return null;
	}

	public String searchId() {
		payment = paymentEJB.load(idSearch);
		return null;
	}

	public String newPayment() {
		payment = new Payment();
		return null;
	}

	public Payment getPayment() {

		if (payment == null) {
			payment = new Payment();
		}

		return payment;
	}

	public Integer getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Integer idSearch) {
		this.idSearch = idSearch;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
