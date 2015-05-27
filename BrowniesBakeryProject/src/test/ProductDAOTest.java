package test;

import dao.ProductDAO;

public class ProductDAOTest {
	public static void main(String[] args) {
		ProductDAO pdao = new ProductDAO();

		System.out.println(pdao.getOldPrice(5));
	}
}
