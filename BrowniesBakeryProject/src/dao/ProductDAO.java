package dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Product;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

@ManagedBean
@SessionScoped
public class ProductDAO implements Serializable {
	private static final long serialVersionUID = -1522920608554312313L;

	public ProductDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Product> getListProducts() {
		Session session = beginTransaction();
		List<Product> list = session.createCriteria(Product.class).list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> listSale(int amount) {
		Session session = beginTransaction();
		Query query = session.createQuery("from Product p where p.sale=1");
		query.setMaxResults(amount);
		List<Product> list = query.list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> listNew(int amount) {
		Session session = beginTransaction();
		Query query = session.createQuery("from Product p order by p.id desc");
		query.setMaxResults(amount);
		List<Product> list = query.list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public double getOldPrice(long id) {
		Session session = beginTransaction();
		Query query = session
				.createQuery("select p.price from HisProduct p where p.product.id = :pid order by p.dateChange desc");
		query.setParameter("pid", id);
		query.setMaxResults(1);

		List<Double> list = query.list();

		commitTransaction(session);
		return (list.isEmpty()) ? -1 : list.get(0).doubleValue();
	}

	private Session beginTransaction() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}

	private void commitTransaction(Session session) {
		Transaction transaction = session.getTransaction();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack() && transaction.isActive()) {
			transaction.commit();
		} else if (transaction.wasRolledBack()) {
			transaction.rollback();
		}
	}
}
