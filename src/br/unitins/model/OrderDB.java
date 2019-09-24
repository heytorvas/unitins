package br.unitins.model;

import br.unitins.model.Customer;
import br.unitins.model.Payment;
import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
public class OrderDB implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Double totalPrice;
	
	@Column
	private LocalDate date;
	
//	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "customer_id_fk")
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name = "order_product", 
	joinColumns = { @JoinColumn(name = "id_order") }, 
	inverseJoinColumns = {@JoinColumn(name = "id_product") })
	private List<Product> product;
	
	private static final long serialVersionUID = 1L;

	public OrderDB() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}   
	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}   
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}  
	
//	public Payment getPayment() {
//		return this.payment;
//	}
//
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}   	
	public List<Product> getProduct() {
		return this.product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
   
}
