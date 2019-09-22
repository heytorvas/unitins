package br.unitins.model;

import br.unitins.model.PaymentType;
import java.io.Serializable;
import java.lang.Integer;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Date date;
	
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
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
   
}
