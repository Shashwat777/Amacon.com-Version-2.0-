import java.util.*;
import java.io.*;

public class Amacon_System {
	public static void exit(Database db) {
		String filename = "Databases.txt";

		try {

			FileOutputStream file = new FileOutputStream(filename);

			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(db);
			out.close();
			file.close();

		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("IOException is caught");
		}

		System.out.println("Total Revenue earned by Amacon");
		System.out.println(db.myrev);

	}

	public static String[] simplify_data(String inp) {

		String option = inp;

		String bchoice = "";
		String prev = "";
		String lastadded = "k";
		for (int ch = 0; ch < option.length(); ch++) {
			char tem = option.charAt(ch);

			if (((int) tem > 95 & (int) tem < 123) || ((int) tem > 64 & (int) tem < 91)
					|| ((int) tem > 48 & (int) tem < 59)) {
				bchoice = bchoice + tem;
				lastadded = Character.toString(tem);

			} else {

				if (lastadded.equals("1")) {

				} else {
					bchoice = bchoice + " ";

					lastadded = "1";
				}

			}

		}
		String[] oplist = bchoice.split(" ");

		return (oplist);

	}

	public static customer getcust(String username, Database db) throws NotEnoughFunds, does_not_exist, out_of_stock {
		Scanner sc = new Scanner(System.in);
		try {
			FileInputStream file = new FileInputStream("customer_objects.txt");

			ObjectInputStream in = new ObjectInputStream(file);

			customer cs = (customer) in.readObject();
			customer t=null;
			System.out.println(cs.un);
			try {
				while (true) {
					if (cs.un.equals(username)) {
						

						t=cs;
						System.out.println(cs.un);
					}
					cs = (customer) in.readObject();
					System.out.println("Welcome" + "   " + username);
				}
			} catch (NullPointerException ex) {

			}

			in.close();
			file.close();

			return t;

		} catch (IOException ex) {

			System.out.println("Enter username of your choice");
			String un = sc.next();

			customer cust = new customer(db, un);

			return cust;

		}
		catch (NullPointerException ex) {

			System.out.println("Enter username of your choice");
			String un = sc.next();

			customer cust = new customer(db, un);

			return cust;

		}
		

		catch (ClassNotFoundException ex) {

			System.out.println("class not found");
			return null;
		}
	}

	public static Database give_database() {
		try {
			FileInputStream file = new FileInputStream("Databases.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			Database db = (Database) in.readObject();

			in.close();
			file.close();

			return db;
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Io");

			Database db = new Database();
			return db;
		}

		catch (ClassNotFoundException ex) {

			Database db = new Database();
			System.out.println("class not found");
			return db;
		} catch (NullPointerException ex) {

			Database db = new Database();
			System.out.println("Null pointer");

			return db;
		}

	}

	public static void main(String[] args) throws AllreadyExists, NotEnoughFunds, does_not_exist, out_of_stock {
		// TODO Auto-generated method stub

		Database db = give_database();
		boolean y = true;
		while (y) {

			System.out.println("Type admin to continue as administrator");
			System.out.println("Type customer to continue as Customer");
			System.out.println("Type exit to Exit the program");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if (input.equals("admin")) {
				administrater adm = new administrater(db);

			} else if (input.equals("customer")) {
				customer cs = null;
				System.out.println("Enter  new ,if new customer");
				System.out.println("Enter old otherwise");
				String old_new = sc.next();

				boolean cnd = true;
				while (cnd) {
					if (old_new.equals("new")) {
						System.out.print("Enter  username of your choice");
						String un = sc.next();
						cs = new customer(db, un);
						cnd = false;
					} else if (old_new.equals("old")) {
						System.out.print("Enter username");
						String un = sc.next();
						cs = getcust(un, db);

						cnd = false;
					} else {
						System.out.println("Wrong Input");
					}

				}
				cs.input();

			}

			else if (input.equals("exit")) {
				exit(db);
				System.exit(0);

			}
		}
	}

}
