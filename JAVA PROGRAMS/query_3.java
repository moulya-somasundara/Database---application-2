/*
 * Name : Moulya Soma Sundara
 * CWID	: ​10415052
 * CS561
 * Query-3
 */

package sales;

// importing packages
import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class Query3 {

	// initializing static variables 
	public static Integer key = new Integer(1);
	public static Hashtable<Integer, ProdQuant> ht = new Hashtable<Integer, ProdQuant>();
	public static Enumeration<Integer> itr;
	public static Integer nextValue;

	public static void main(String[] args) {
		// database connection configuration
		String usr = "postgres";
		String pwd = "hanumanp";
		String url = "jdbc:postgresql://localhost:5432/postgres";

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Successfully loaded the driver!");			
		} catch (Exception e) {
			System.out.println("Fail loading driver!");
			e.printStackTrace();
		}

		// connection server
		try {
			// opening connection
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			// get query rs to ResultsSet rs
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			// traverse rs through each element
			while (rs.next()) {

				// storing values in object
				ProdQuant r3 = new ProdQuant();
				r3.setCustomer(rs.getString("cust"));
				r3.setProduct(rs.getString("prod"));
				r3.setMonth(rs.getInt("month"));
				r3.setQty(rs.getInt("quant"));

				if (r3.getMonth() > 0 && r3.getMonth() < 13) {
					r3.setMonthTotal(r3.getMonth()-1, r3.getQty());
					r3.setMonthTarget(r3.getQty() / 3);

					// calculating 1/3 of total sales
					Calculate(r3);
				}
			}

			// displaying output
			System.out.println(String.format("%-8s", "CUSTOMER") + " " + String.format("%-7s", "PRODUCT") + "  " 
					+ String.format("%-22s", "1/3 PURCHASED BY MONTH"));
			System.out.println("======== =======  ======================");

			itr = ht.keys();
			while(itr.hasMoreElements()) {
				nextValue = (Integer) itr.nextElement();

				boolean isMinMonthPrinted = false;
				int targetAchieved = 0;
				for (int i = 0; i < 12; i++) {
					targetAchieved += ht.get(nextValue).getMonthTotal(i);
					
					if (targetAchieved >= ht.get(nextValue).getMonthTarget() &&
							isMinMonthPrinted == false) {

						System.out.println(String.format("%-8s", ht.get(nextValue).getCustomer()) + " " + String.format("%-7s", ht.get(nextValue).getProduct()) + "  " 
								+ String.format("%-22s", i+1));
						
						isMinMonthPrinted = true;
					}					
				}
			}
						
			// closing connection
			conn.close();
		} catch (SQLException ex) {
			System.out.println("Connection URL or username or password errors!");
			ex.printStackTrace();
		}
	}
	
	public static void Calculate(ProdQuant r3) {
		boolean addNewFlag = true;
		
		if (ht.isEmpty()) {						// adding values in hash table if null
			ht.put(key, r3);
			key++;
		} else {								// traversing hash table for comparison and modification
			itr = ht.keys();
			while(itr.hasMoreElements()) {		// iterating hash table
				nextValue = (Integer) itr.nextElement();
				
				if (r3.getCustomer().equalsIgnoreCase(ht.get(nextValue).getCustomer()) && 
						r3.getProduct().equalsIgnoreCase(ht.get(nextValue).getProduct())) {
					addNewFlag = false;

					ht.get(nextValue).setMonthTotal(r3.getMonth()-1, ht.get(nextValue).getMonthTotal(r3.getMonth()-1) + r3.getQty());
					ht.get(nextValue).setMonthTarget(ht.get(nextValue).getMonthTarget() + r3.getMonthTarget());		
				}
			}
			
			if (addNewFlag) {
				ht.put(key, r3);
				key++;
			}	
		}
	}
}
class ProdQuant {
	private String customer;
	private String product;
	private int month;
	private int qty;
	
	private int[] monthTotal = new int[12];
	private int monthTarget;
		
	// getters
	public String getCustomer() {
		return customer;
	}
	
	public String getProduct() {
		return product;
	}
	
	public int getQty() {
		return qty;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getMonthTotal(int index) {
		return monthTotal[index];
	}
	
	public int getMonthTarget() {
		return monthTarget;
	}
	
		
	// setters
	public void setCustomer(String customerName) {
		this.customer = customerName;
	}
	
	public void setProduct(String productName) {
		this.product = productName;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setMonthTotal(int index, int monthTotal) {
		this.monthTotal[index] = monthTotal;
	}
	
	public void setMonthTarget(int monthTarget) {
		this.monthTarget = monthTarget;
	}
	
	
	// initializing values
	public ProdQuant() {
		customer = null;
		product = null;
		month = -1;
		monthTarget = 0;
	}
}
