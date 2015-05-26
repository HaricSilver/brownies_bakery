package test;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Bill;
import model.Product;
import model.User;

import org.hibernate.Session;

import util.HibernateUtil;

public class BillTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		User user = new User();
		user.setName("Haric Silver");
		user.setPassword("123456");
		user.setAddress("Khong co");
		user.setPhone("Khong biet");

		Product p = new Product();
		p.setName("Banh kem 1");
		p.setPrice(350000);
		p.setMainImage("test 1");
		Product p2 = new Product();
		p2.setName("Banh kem 2");
		p2.setPrice(350000);
		p2.setMainImage("test 2");

		List<Product> products = new LinkedList<Product>();
		products.add(p);
		products.add(p2);

		Bill bill = new Bill();
		bill.setUser(user);
		bill.setRecipient("Haric Silver");
		bill.setCreateDate(Calendar.getInstance());
		bill.setDeliveryDate(Calendar.getInstance());
		bill.setProducts(products);

		session.save(user);
		session.save(p);
		session.save(p2);
		session.save(bill);

		session.getTransaction().commit();
	}
}
