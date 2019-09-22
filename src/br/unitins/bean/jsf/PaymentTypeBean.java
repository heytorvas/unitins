package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.bean.ejb.PaymentTypeEJB;
import br.unitins.model.PaymentType;

@Named
@RequestScoped
public class PaymentTypeBean {

	@EJB
	private PaymentTypeEJB paymentTypeEJB;

	private PaymentType paymentType;

	private Integer idSearch;

	private List<PaymentType> paymentTypes;

	@PostConstruct
	public void init() {
		paymentTypes = paymentTypeEJB.findAll();
	}

	public String insert() {
		paymentTypeEJB.insert(paymentType);
		return null;
	}

	public String update() {
		paymentTypeEJB.update(paymentType);
		return null;
	}

	public String delete() {
		paymentTypeEJB.delete(paymentType);
		return null;
	}

	public String searchId() {
		paymentType = paymentTypeEJB.load(idSearch);
		return null;
	}

	public String newPaymentType() {
		paymentType = new PaymentType();
		return null;
	}

	public PaymentType getPaymentType() {

		if (paymentType == null) {
			paymentType = new PaymentType();
		}

		return paymentType;
	}

	public Integer getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Integer idSearch) {
		this.idSearch = idSearch;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
}
