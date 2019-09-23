package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.bean.ejb.PaymentEJB;
import br.unitins.bean.ejb.PaymentTypeEJB;
import br.unitins.model.Payment;
import br.unitins.model.PaymentType;

@Named
@RequestScoped
public class PaymentBean {
	
	@EJB
	private PaymentEJB paymentEJB;

	@EJB
	private PaymentTypeEJB paymentTypeEJB;
	
	private Payment payment;

	private Integer idSearch;
	
	private Integer idPaymentType;

	private List<Payment> payments;
	
	private List<PaymentType> paymentTypes;
	
	@PostConstruct
	public void init() {
		payments = paymentEJB.findAll();
		setPaymentTypes(paymentTypeEJB.findAll());
	}

	public String insert() {
		paymentEJB.insert(payment, idPaymentType);
		return null;
	}

	public String update() {
		paymentEJB.update(payment);
		return null;
	}

	public String delete() {
		paymentEJB.delete(paymentEJB.load(idSearch));
		return null;
	}

	public String searchId() {
		payment = paymentEJB.load(idSearch);
		return null;
	}

	public String clean() {
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

	public Integer getIdPaymentType() {
		return idPaymentType;
	}

	public void setIdPaymentType(Integer idPaymentType) {
		this.idPaymentType = idPaymentType;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
}
