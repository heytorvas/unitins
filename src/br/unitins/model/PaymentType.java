package br.unitins.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
public class PaymentType implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer id;
	
	private String label;
	private static final long serialVersionUID = 1L;

	public PaymentType() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
   
}
