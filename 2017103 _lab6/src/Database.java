import java.util.*;
import java.io.*;

public final class Database implements java.io.Serializable {
	int myrev;
	private static final long serialversionUID = 129348938L;

	List<products> pmain_list = new LinkedList<products>();
	LinkedList<String> pmain_list_name = new LinkedList<String>();
	private LinkedList<products> plist = new LinkedList<products>();
	private LinkedList<String> pnames = new LinkedList<String>();
	int plength;

	Database() {
	}

	void add(String[] path) throws AllreadyExists {

		LinkedList<products> parentlist = new LinkedList<products>();
		List<String> seq = new ArrayList<String>();
		try {

			LinkedList<products> templist = plist;
			LinkedList<String> tempnames = pnames;
			String pth = "";

			products current = null;

			for (int k = 1; k < path.length; k++) {

				if (path[k].equals(" ")) {
				} else {
					plength++;
					if ((current != null && k == path.length - 1 && current.pnames.contains(path[k])
							|| (k == path.length - 1 && pnames.contains(path[k])))) {

						throw new AllreadyExists();
					} else {

						if (k != 1) {
							pth = pth + ">" + path[k];
						} else {
							pth = path[k];
						}

						if (tempnames.contains(path[k])) {

							int inde = tempnames.indexOf(path[k]);
							current = templist.get(inde);
							templist = current.plist;
							tempnames = current.pnames;

						} else {

							if (current != null) {
								current.add(path[k], pth, k, path, parentlist, this);

								int inde = current.pnames.indexOf(path[k]);
								current = current.plist.get(inde);
								templist = current.plist;
								tempnames = current.pnames;

							}

							else {

								products main = new products(path[k], pth, null, parentlist, this);
								plist.add(main);
								pnames.add(path[k]);
								pmain_list.add(main);
								pmain_list_name.add(path[k]);

								current = main;
								templist = current.plist;
								tempnames = current.pnames;

							}
						}
					}
				}
				if (current != null) {
					parentlist.add(current);
				}

			}

		} catch (AllreadyExists e) {
			System.out.println("Product exists of the same type");
			throw new AllreadyExists();

		}
	}

	 products search(String to_be_searched) throws does_not_exist {
		products found = null;

		if (pmain_list_name.contains(to_be_searched)) {

			int inde = pmain_list_name.indexOf(to_be_searched);

			found = pmain_list.get(inde);

			return (found);
		}
		if (found == null) {
			throw new does_not_exist();
		}
		return found;

	}

	void modify(String to_be_modified) throws does_not_exist {
		Scanner sc = new Scanner(System.in);

		System.out.println("type price to change price ");
		System.out.println("type qnty to change quantity ");

		String inp = sc.next();

		int val = sc.nextInt();
		if (inp.equals("price")) {
			System.out.println("Enter  price ");
			products tmp = search(to_be_modified);
			tmp.modify_price(val);

		} else if (inp.equals("qnty")) {
			System.out.println("Enter new qnty ");
			products tmp = search(to_be_modified);
			tmp.modify_quantity(val);

		}

	}

	void delete(String to_be_deleted) throws does_not_exist {
		try {
			products dlt = search(to_be_deleted);
			System.out.print(dlt.name);
			pmain_list.remove(dlt);
			pmain_list_name.remove(dlt.name);
			plength--;
			if (dlt.parent != null) {
				products parent = dlt.parent;
				parent.plist.remove(dlt);
				parent.pnames.remove(dlt.name);
				System.out.print(dlt.parent.name);
			}
			System.out.println(plength);

			for (int kl = 0; kl < plength; kl++) {

				if ((pmain_list.get(kl) != null) && pmain_list.get(kl).parentlist.contains(dlt)) {
					pmain_list.remove(kl);
					pmain_list_name.remove(kl);
				}

			}
		} catch (NullPointerException e) {
			throw new does_not_exist();
		}

	}

	void sale(ArrayList<products> pr, int funds, int bill) throws NotEnoughFunds {
		

			if (funds < bill) {
		

				throw new NotEnoughFunds(bill - funds);

			} else {
				System.out.print("Transection completed !! Thanks for shopping");
				myrev = myrev + bill;
			}

		
	}
}
