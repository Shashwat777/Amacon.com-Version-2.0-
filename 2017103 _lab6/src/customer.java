import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public final class customer implements java.io.Serializable {
     int funds;
	private ArrayList<products> cart = new ArrayList<products>();
	private Database db;
	 int bill;
	String un;
	private static final long serialversionUID = 189L; 


	customer(Database db, String un) throws NotEnoughFunds ,does_not_exist , out_of_stock {

		this.db = db;
	
		this.un = un;

	}

	void add_funds(int fund_tobeadded) throws does_not_exist {
		this.funds = this.funds + fund_tobeadded;
	}

	void check_out() throws NotEnoughFunds {
		db.sale(cart, funds, bill);
	}
	void exit() {

		String filename = "customer_objects.txt";

		try {

			FileOutputStream file = new FileOutputStream(filename, true);

			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this);
			
			
		
	
		

		
			
			out.close();
			file.close();

		} catch (IOException ex) {

			
		} 
	}
	void add_product(String pr) throws does_not_exist, out_of_stock {
		

	

		String pro_name = pr;
		products pro = db.search(pro_name);
		if (pro != null ) {
			if(pro.quantity>0) {
			cart.add(pro);
			bill = bill + pro.price;
			pro.quantity=pro.quantity-1;
			}
			else {
				
				throw new out_of_stock();
			}
		} else {
			System.out.println("Please enter a valid product");
		}

	
		

	}

	void input() throws NotEnoughFunds ,does_not_exist, out_of_stock {

		System.out.println("add funds");
		System.out.println("add product to the cart");
		System.out.println("check-out cart");
		System.out.println("Exit as customer");
		Scanner sc = new Scanner(System.in);
		boolean cust = true;

		while (cust) {
			try {

				String oplist = sc.nextLine();
				if (oplist.equals("add funds")) {
					System.out.println("enter amount");
					int amount = sc.nextInt();

					add_funds(amount);
					System.out.println("Updated funds are :" + " " + funds);

				} else if (oplist.equals("add product")) {
					System.out.println("Enter product name");
					String name=sc.next();
					add_product(name);
				} else if (oplist.equals("check-out")) {

					check_out();

				} else if (oplist.equals("exit")) {

					cust = false;
					exit();
						
					
				} else {
				}

			} catch (ArrayIndexOutOfBoundsException e) {
			}

		}
	}
}
