package br.unitins.model;

import br.unitins.model.PaymentType;
import java.io.Serializable;
import java.lang.Integer;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
//	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	@OneToOne
	@JoinColumn(name = "order_id_fk")
	private OrderDB orderDB;

	@ManyToOne
	@JoinColumn(name = "paymentType_id_fk")
	private PaymentType paymentType;
	
	private static final long serialVersionUID = 1L;

	public Payment() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}   
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	public OrderDB getOrderDB() {
		return orderDB;
	}
	
	public void setOrderDB(OrderDB orderDB) {
		this.orderDB = orderDB;
	}
}
