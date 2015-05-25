package test;

import model.Product;

import org.hibernate.Session;

import util.HibernateUtil;

public class ProductTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Product p = new Product();
		p.setId(1);
		p.setPrice(350000);
		String[] imgs = { "test", "khong test", "test tiep" };
		p.setImages(imgs);

		session.saveOrUpdate(p);
		System.out.println(session.createCriteria(Product.class).list());
		// System.out.println(p.getId());

		session.getTransaction().commit();
	}
}
