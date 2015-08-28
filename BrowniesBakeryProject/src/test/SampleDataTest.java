package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.hibernate.Session;

import model.Account;
import model.Category;
import model.HisProduct;
import model.Manufacturer;
import model.Product;
import model.User;
import util.Encrypter;
import util.HibernateUtil;

public class SampleDataTest {

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

		// manufacturer
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName("KMC");
		manufacturer.setAddress("Tổ 5, Khu phố 2, Phường Linh Xuân, Quận Thủ Đức");
		session.save(manufacturer);

		// sample catelory
		Category category = new Category();
		category.setName("Bánh kem sinh nhật");
		category.setUnit("Cái");
		session.save(category);

		// sample account
		Account account = new Account();
		account.setName("Haric Silver");
		account.setPassword(Encrypter.encryptMD5("123456"));
		account.setType(1);
		session.save(account);

		// sample user
		User user = new User();
		user.setFullName("Haric Silver");
		user.setAddress("Khong co");
		user.setPhone("Khong biet");
		session.save(user);

		// sample product
		Product p0;
		int j = 0;
		Random rd = new Random();
		for (int i = 0; i < 45; i++) {
			p0 = new Product();
			p0.setCategory(category);
			p0.setManufacturer(manufacturer);
			p0.setName("Bánh kem " + (i + 1));
			p0.setMainImage((j++ % 10) + ".jpg");
			p0.setPrice(rd.nextInt(300000) + 150000);
			p0.setSale(rd.nextInt(3) == 2);
			session.save(p0);

			if (p0.isSale()) {
				HisProduct hisProduct = new HisProduct();
				hisProduct.setProduct(p0);
				hisProduct.setDateChange(Calendar.getInstance());
				hisProduct.setName("Banh kem " + i);
				hisProduct.setPrice(p0.getPrice() + rd.nextInt(250000));
				session.save(hisProduct);
			}
		}

		// Product p = new Product();
		// p.setCategory(category);
		// p.setManufacturer(manufacturer);
		// p.setName("Bánh kem 1");
		// p.setMainImage("1.jpg");
		// p.setPrice(350000);
		// session.save(p);
		//
		// Product p2 = new Product();
		// p2.setCategory(category);
		// p2.setManufacturer(manufacturer);
		// p2.setName("Bánh kem 2");
		// p2.setMainImage("2.jpg");
		// p2.setSale(true);
		// p2.setPrice(350000);
		// session.save(p2);
		//
		// // sample history product
		// HisProduct hisProduct = new HisProduct();
		// hisProduct.setProduct(p);
		// hisProduct.setDateChange(Calendar.getInstance());
		// hisProduct.setName("Banh kem 1");
		// hisProduct.setPrice(900000);
		// session.save(hisProduct);
		//
		// HisProduct hisProduct1 = new HisProduct();
		// hisProduct1.setProduct(p2);
		// hisProduct1.setDateChange(calendar);
		// hisProduct1.setName("Banh kem 2");
		// hisProduct1.setPrice(400000);
		// session.save(hisProduct1);

		// sample order
		// Order order = new Order();
		// order.setUser(user);
		// order.setRecipient("Haric Silver");
		// order.setCreateDate(Calendar.getInstance());
		// order.setDeliveryDate(Calendar.getInstance());
		// order.addProduct(p);
		// order.addProduct(p2);
		// session.save(order);

		session.getTransaction().commit();
	}
}
