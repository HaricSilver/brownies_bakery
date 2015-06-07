package test;

import dao.OrderDAO;

public class OrderTest {

	public static void main(String[] args) {
		// Account account = new Account();
		// account.setName("Haric Silver");
		// account.setPassword("123456");
		//
		// User user = new User();
		// user.setFullName("Haric Silver");
		// user.setAddress("Khong co");
		// user.setPhone("Khong biet");
		//
		// Bill bill = new Bill();
		// bill.setUser(user);
		// bill.setRecipient("Haric Silver");
		// bill.setCreateDate(Calendar.getInstance());
		// bill.setDeliveryDate(Calendar.getInstance());
		//
		// Product p = new Product();
		// p.setId(1);
		// Product p2 = new Product();
		// p2.setId(1);
		//
		// bill.addProduct(p);
		// bill.addProduct(p2);
		//
		// Map<String, Integer> products = new HashMap<String, Integer>();
		// products.put("psdf", new Integer(2));
		//
		// System.out.println(bill.getProducts().keySet());

		OrderDAO billdao = new OrderDAO();
		System.out.println(billdao.list().get(0));
	}

}
