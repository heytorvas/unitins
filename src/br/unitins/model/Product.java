package br.unitins.model;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String nameProduct;
	@Column
	private Double price;
	
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNameProduct() {
		return this.nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}   
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
   
}
