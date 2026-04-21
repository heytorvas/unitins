package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.application.Util;
import br.unitins.bean.ejb.OrderEJB;
import br.unitins.bean.ejb.PaymentEJB;
import br.unitins.bean.ejb.PaymentTypeEJB;
import br.unitins.model.OrderDB;
import br.unitins.model.Payment;
import br.unitins.model.PaymentType;

@Named
@RequestScoped
public class PaymentBean {
	
	@EJB
	private PaymentEJB paymentEJB;

	@EJB
	private PaymentTypeEJB paymentTypeEJB;
	
	@EJB
	private OrderEJB orderEJB;
	
	private Payment payment;

	private Integer idSearch;
	
	private Integer idPaymentType;
	
	private Integer idOrder;

	private List<Payment> payments;
	
	private List<PaymentType> paymentTypes;
	
	private List<OrderDB> orders;
	
	private List<OrderDB> testando;
	
	private List<Integer> numero;
	
	@PostConstruct
	public void init() {
		payments = paymentEJB.findAll();
		setOrders(orderEJB.findAll());
		setPaymentTypes(paymentTypeEJB.findAll());
	}

	public String insert() {
		paymentEJB.insert(payment, idOrder, idPaymentType);
		clean();
		Util.redirect("payment.xhtml");
		return null;
	}

	public String update() {
		payment.setId(getIdSearch());
		paymentEJB.update(payment);
		clean();
		Util.redirect("payment.xhtml");
		return null;
	}

	public String delete() {
		paymentEJB.delete(paymentEJB.load(idSearch));
		clean();
		Util.redirect("payment.xhtml");
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
		
//		setTestando(getOrders());
//		for (int i = 0; i < getTestando().size(); i++) {
//			if (getTestando().contains(idOrder != null)) {
//				getTestando().remove(i);
//			}
//		}

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

	public List<OrderDB> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDB> orders) {
		this.orders = orders;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public List<OrderDB> getTestando() {
		return testando;
	}

	public void setTestando(List<OrderDB> testando) {
		this.testando = testando;
	}

	public List<Integer> getNumero() {
		return numero;
	}

	public void setNumero(List<Integer> numero) {
		this.numero = numero;
	}
	
}
