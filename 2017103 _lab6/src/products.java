import java.util.*;
import java.io.*;
public  final class products implements java.io.Serializable{
	private static final long serialversionUID = 1L; 
	int price;
	int quantity;
	String path;
	String name;
	String[] seq;
	int g=0;
	LinkedList<products> plist = new LinkedList<products>();
	LinkedList<String> pnames = new LinkedList<String>();
	LinkedList<products> parentlist = new LinkedList<products>();
	products parent;

	products(String name, String pth, products parent,LinkedList<products> parents ,Database my_db) {
		this.name = name;
		
		my_db.pmain_list.add(this);
		my_db.pmain_list_name.add(this.name);
		this.parent=parent;
		LinkedList<products> parentlist=parents;
		if(parent!=null) {
		
		this.path=pth;}

	}

	void add(String nam, String pth , int k , String[] lst,LinkedList<products> parents,Database db) throws AllreadyExists{
		
		g++;
		
		
		products adding = new products(nam, pth , this, parents,db);

	
		plist.add(adding);
		pnames.add(nam);
		
		
	}

	void modify_price(int pr) {
		this.price = pr;
		System.out.println("Price Modified to:" + pr);

	}

	void modify_quantity(int qnty) {
		this.quantity = qnty;
		System.out.println("Quantity Modified to:" + qnty);
	}

	boolean if_exist(String name) {
		return pnames.contains(name);

	}

	void delete() {
		parent.plist.remove(this);
		parent.pnames.remove(this.name);
	}

	void when_found() {

		
		System.out.println(this.path);
		System.out.println(this.name);
		System.out.println("Product Price :"+ this.price);
		System.out.println("Product Quantity"+ this.quantity);
		

	}

}
