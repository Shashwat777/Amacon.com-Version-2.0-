import java.util.*;
// insert("shashwat" > "jain" > "family" )

public class administrater implements java.io.Serializable {
	Database db;

	administrater(Database db) throws AllreadyExists, does_not_exist {
		System.out.println("Insert product/category");
		System.out.println(" Delete product/category");
		System.out.println("Search product");
		System.out.println("Modify product");
		System.out.println("Exit as administrator");
		this.db = db;
		input();
	}

	public void input() throws AllreadyExists, does_not_exist {
		boolean admin = true;
		Scanner sc = new Scanner(System.in);
		while (admin) {

			String inp = sc.nextLine();
			String[] oplist = Amacon_System.simplify_data(inp);
			String choice = oplist[0];

			if (choice.equals("insert")) {

				db.add(oplist);

			} else if (choice.equals("delete")) {
				db.delete(oplist[1]);
			} else if (choice.equals("search")) {
				try {
					System.out.println(oplist[1]);

					products myfind = db.search(oplist[1]);
					myfind.when_found();
				} catch (NullPointerException e) {
					throw new does_not_exist();

				}

			} else if (choice.equals("modify")) {
				db.modify(oplist[1]);

			} else if (choice.equals("exit")) {
				admin = false;
			}

		}

	}
}