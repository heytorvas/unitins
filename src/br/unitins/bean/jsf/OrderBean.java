package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.application.Util;
import br.unitins.bean.ejb.CustomerEJB;
import br.unitins.bean.ejb.OrderEJB;
import br.unitins.bean.ejb.PaymentEJB;
import br.unitins.bean.ejb.PaymentTypeEJB;
import br.unitins.bean.ejb.ProductEJB;
import br.unitins.model.Customer;
import br.unitins.model.OrderDB;
import br.unitins.model.Payment;
import br.unitins.model.PaymentType;
import br.unitins.model.Product;

@Named
@RequestScoped
public class OrderBean {
	@EJB
	private OrderEJB orderEJB;
	
	@EJB
	private CustomerEJB customerEJB;
	
	@EJB
	private PaymentEJB paymentEJB;
	
	@EJB
	private ProductEJB productEJB;

	private OrderDB order;

	private Integer idSearch;
	
	private Integer idCustomer;
	
	private Integer idPayment;
	
	private List<Product> products;

	private List<OrderDB> orders;
	
	private List<Customer> customers;
	
	private List<Payment> payments;
	
	@PostConstruct
	public void init() {
		orders = orderEJB.findAll();
		setPayments(paymentEJB.findAll());
		setCustomers(customerEJB.findAll());
		setProducts(productEJB.findAll());
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String insert() {
		orderEJB.insert(order, idCustomer, idPayment, products);
		clean();
		Util.redirect("order.xhtml");
		return null;
	}
	
//	public String insert() {
//		orderEJB.insert(order, idCustomer, idPayment);
//		clean();
//		Util.redirect("order.xhtml");
//		return null;
//	}

	public String update() {
		order.setId(getIdSearch());
		orderEJB.update(order);
		clean();
		Util.redirect("order.xhtml");
		return null;
	}

	public String delete() {
		orderEJB.delete(orderEJB.load(idSearch));
		clean();
		Util.redirect("order.xhtml");
		return null;
	}

	public String searchId() {
		order = orderEJB.load(idSearch);
		return null;
	}

	public String clean() {
		order = new OrderDB();
		return null;
	}
	
	public OrderDB getOrder() {

		if (order == null) {
			order = new OrderDB();
		}

		return order;
	}

	public Integer getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Integer idSearch) {
		this.idSearch = idSearch;
	}

	public List<OrderDB> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDB> orders) {
		this.orders = orders;
	}
	
	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Integer getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(Integer idPayment) {
		this.idPayment = idPayment;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
