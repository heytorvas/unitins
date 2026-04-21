package br.unitins.bean.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unitins.model.Product;

@Stateful
public class ProductEJB {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Product product) {
		em.persist(product);
	}

	public void update(Product product) {
		em.merge(product);
	}

	public void delete(Product product) {
		product = load(product.getId());
		em.remove(product);
	}

	public Product load(Integer id) {
		return em.find(Product.class, id);
	}

	public List<Product> findAll() {
		return em.createQuery("select p from Product p", Product.class).getResultList();
}
}
