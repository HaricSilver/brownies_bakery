package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Account;
import model.Bill;
import model.HisProduct;
import model.Product;
import model.User;

import org.hibernate.Session;

import util.HibernateUtil;

public class MainTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date d;
		Calendar calendar = null;
		try {
			d = simpleDateFormat.parse("25/5/2014");
			calendar = Calendar.getInstance();
			calendar.setTime(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Account account = new Account();
		account.setName("Haric Silver");
		account.setPassword("123456");

		User user = new User();
		user.setFullName("Haric Silver");
		user.setAddress("Khong co");
		user.setPhone("Khong biet");

		Product p = new Product();
		p.setName("Banh kem 1");
		p.setMainImage("1.jpg");
		p.setPrice(350000);
		Product p2 = new Product();
		p2.setName("Banh kem 2");
		p2.setMainImage("2.jpg");
		p2.setSale(true);
		p2.setPrice(350000);

		HisProduct hisProduct = new HisProduct();
		hisProduct.setProduct(p);
		hisProduct.setDateChange(Calendar.getInstance());
		hisProduct.setName("his product");
		hisProduct.setPrice(900000);
		HisProduct hisProduct1 = new HisProduct();
		hisProduct1.setProduct(p);
		hisProduct1.setDateChange(calendar);
		hisProduct1.setName("his product");
		hisProduct1.setPrice(10000);

		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(p, new Integer(2));
		products.put(p2, new Integer(1));

		Bill bill = new Bill();
		bill.setUser(user);
		bill.setRecipient("Haric Silver");
		bill.setCreateDate(Calendar.getInstance());
		bill.setDeliveryDate(Calendar.getInstance());
		bill.setProducts(products);

		session.save(account);
		session.save(user);
		session.save(p);
		session.save(p2);
		session.save(hisProduct);
		session.save(hisProduct1);
		session.save(bill);

		session.getTransaction().commit();
	}
}
