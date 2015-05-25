package dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import model.Product;

@ManagedBean
@SessionScoped
public class ProductDAO {

	public ProductDAO() {
	}

	public List<Product> getListProducts() {
		Session session = beginTransaction();
		@SuppressWarnings("unchecked")
		List<Product> list = session.createCriteria(Product.class).list();
		commitTransaction(session);
		return list;
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
