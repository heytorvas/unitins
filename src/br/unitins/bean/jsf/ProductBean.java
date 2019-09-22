package br.unitins.bean.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.bean.ejb.ProductEJB;
import br.unitins.model.Product;

@Named
@RequestScoped
public class ProductBean {
	
	@EJB
	private ProductEJB productEJB;
	
	private Product product;
	
	private Integer idSearch;
	
	private List<Product> products;
	
	@PostConstruct
	public void init() {
		products = productEJB.findAll();
	}

	public String insert() {
		productEJB.insert(product);
		return null;
	}

	public String update() {
		productEJB.update(product);
		return null;
	}

	public String delete() {
		productEJB.delete(product);
		return null;
	}

	public String searchId() {
		product = productEJB.load(idSearch);
		return null;
	}

	public String newProduct() {
		product = new Product();
		return null;
	}

	public Product getProduct() {

		if (product == null) {
			product = new Product();
		}

		return product;
	}

	public Integer getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Integer idSearch) {
		this.idSearch = idSearch;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
