package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.application.Util;
import br.unitins.bean.ejb.CustomerEJB;
import br.unitins.model.Customer;

@Named
@RequestScoped
public class CustomerBean {
	
	@EJB
	private CustomerEJB customerEJB;

	private Customer customer;

	private Integer idSearch;

	private List<Customer> customers;

	@PostConstruct
	public void init() {
		customers = customerEJB.findAll();
	}

	public String insert() {
		customerEJB.insert(customer);
		clean();
		Util.redirect("customer.xhtml");
		return null;
	}

	public String update() {
		customer.setId(getIdSearch());
		customerEJB.update(customer);
		clean();
		Util.redirect("customer.xhtml");
		return null;
	}

	public String delete() {
		customerEJB.delete(customerEJB.load(idSearch));
		clean();
		Util.redirect("customer.xhtml");
		return null;
	}

	public String searchId() {
		customer = customerEJB.load(idSearch);
		return null;
	}

	public String clean() {
		customer = new Customer();
		return null;
	}

	public Customer getCustomer() {

		if (customer == null) {
			customer = new Customer();
		}

		return customer;
	}

	public Integer getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Integer idSearch) {
		this.idSearch = idSearch;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
