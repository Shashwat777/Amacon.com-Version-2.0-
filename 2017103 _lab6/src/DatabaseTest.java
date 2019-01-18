import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseTest {

	Database db = new Database();
	//@BeforeAll
	
	

	@Test
	void testadd() {
		String[] m = Amacon_System.simplify_data("insert electronics > smartphone Oneplus)​");

		AllreadyExists exception = assertThrows(AllreadyExists.class, () -> {

			db.add(m);
			db.add(m);

		});
	}

	@Test
	void testdelete() {
		String[] m = Amacon_System.simplify_data("delete gh");

		does_not_exist exception = assertThrows(does_not_exist.class, () -> {

			db.delete(m[1]);

		});
	}

	@Test
	void testsearch() {
		String[] m = Amacon_System.simplify_data("search gh");

		does_not_exist exception = assertThrows(does_not_exist.class, () -> {

			db.search(m[1]);

		});
	}

	@Test
	void test_out_of_stock() {
		String[] m = Amacon_System.simplify_data("search gh");

		out_of_stock exception = assertThrows(out_of_stock.class, () -> {

			customer cust_test = new customer(db, "Ram");
			db.add(Amacon_System.simplify_data("insert iphone"));
			cust_test.add_product("iphone");

		});

	}
	@Test
	void test2_out_ofstock() throws AllreadyExists, does_not_exist, NotEnoughFunds, out_of_stock {
		Database db = new Database();
		db.add(Amacon_System.simplify_data("insert balloons"));
		
		customer cust = new customer(db, "Gupta");
		
	

	

		out_of_stock exception = assertThrows(out_of_stock.class, () -> {
			cust.add_product("balloons");

		});

	}

	@Test
	void test_not_enough_funds() {
		String[] m = Amacon_System.simplify_data("search gh");

		NotEnoughFunds exception = assertThrows(NotEnoughFunds.class, () -> {

			customer cust_test = new customer(db, "Ram");
			db.add(Amacon_System.simplify_data("insert macbook"));
			products t=db.search("macbook");
			t.price=40000;
			t.quantity=2;
			cust_test.add_product("macbook");
	
		
			cust_test.check_out();

		});

	}
}
