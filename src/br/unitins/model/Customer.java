package br.unitins.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String nameCustomer;
	
	@Column
	private String email;
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNameCustomer() {
		return this.nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
