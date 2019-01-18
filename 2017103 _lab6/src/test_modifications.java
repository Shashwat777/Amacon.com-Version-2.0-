import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test_modifications {
	Database db = new Database();

	@Test
	void testDatabaseSerialization() throws AllreadyExists, does_not_exist {
		Database db = new Database();
		db.add(Amacon_System.simplify_data("insert > stationary > pen"));
		products tester = db.search("pen");

		Amacon_System.exit(db);
		Database db1 = Amacon_System.give_database();

		AllreadyExists exception = assertThrows(AllreadyExists.class, () -> {
			db.add(Amacon_System.simplify_data("insert > stationary > pen"));

		});

	}

	@Test
	void testDatabaseSerialization2() throws AllreadyExists, does_not_exist {
		Database db = new Database();
		db.add(Amacon_System.simplify_data("insert > stationary > pencil"));

		Amacon_System.exit(db);
		Database db1 = Amacon_System.give_database();

		products tester = db1.search("pencil");
		assertFalse(tester == null);

	}

	@Test
	void testDatabaseSerialization3() throws AllreadyExists, does_not_exist {
		Database db = new Database();
		db.add(Amacon_System.simplify_data("insert > stationary > eraser"));
		products tester1 = db.search("eraser");
		tester1.price = 100;

		Amacon_System.exit(db);
		Database db1 = Amacon_System.give_database();

		products tester2 = db1.search("eraser");

		Amacon_System.exit(db);
		assertTrue(tester1.price == tester2.price);

	}

	@Test
	void testCustomerSerialization() throws AllreadyExists, does_not_exist, NotEnoughFunds, out_of_stock {

		customer cust = new customer(db, "shash");
		cust.add_funds(100);

		cust.exit();
		customer cust1 = Amacon_System.getcust("shash", db);
		assertTrue(cust1.funds == 100);
	

	}

	@Test
	void testCustomerSerialization2() throws
	AllreadyExists, does_not_exist, NotEnoughFunds, out_of_stock{
		customer cust = new customer(db, "shashwat");
		cust.add_funds(100);

		cust.exit();
		customer cust1 = Amacon_System.getcust("shashwat", db);
		assertTrue(cust1.funds == 100);

		/*db.add(Amacon_System.simplify_data("insert balloons"));
		
		customer cust = new customer(db, "jain");
		products mypro=db.search("balloons");
		mypro.price=100;
		mypro.quantity=3;
		
		cust.add_product("balloons");

		cust.exit();
	
		customer cust1 = Amacon_System.getcust("jain",db);
		

		NotEnoughFunds exception = assertThrows(NotEnoughFunds.class, () -> {
			cust1.check_out();

		}); */

	}
}

