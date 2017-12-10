/*Name: Moulya Soma Sundara
 *CWID: 10415052
 *Query-2
 *month=1,2,3,4.....12 represents January,February,March,......December
 *Same for M1_total,M2_total....M12_total and m1_count,m2_count,m3_count....m12_count used for each month's total and count. 
 * Algorithm:
 * Initialize an array list at the beginning which will store our values.
 * use of Array list will give us values in order unlike the hash tables.
 * scans months for the customer and products.
 * then update them according to which month they fall in.
 * then calculate average for the months.
 * display the result for the previous month and after month for that customer and product for every month.
*/
package sales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Query2 {
	static ArrayList<Report2> query2=new ArrayList<Report2>();
public static void main(String [] args)
{
	String usr ="postgres";
    String pwd ="hanumanp";
    String url ="jdbc:postgresql://localhost:5432/postgres";
	try{
		Class.forName("org.postgresql.Driver");
		System.out.println("Success loading Driver!");
	}catch(Exception e)
	{
		System.out.println("Failed to load drivers!");
	}
	try {
		Connection com=DriverManager.getConnection(url, usr, pwd);
		Statement st=com.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Sales");
		while(rs.next())
		{
			int count=0;
			int len=query2.size();
			for(int i=0;i<len;i++)
			{
				//Scans and updates customer-product details based on the month it falls.
				if(query2.get(i).custName.equals(rs.getString("cust")) && query2.get(i).prodName.equals(rs.getString("prod")))
				{ //calculate total for month and their count.
					if(rs.getInt("month")==1)
					{
						query2.get(i).M1_total+=rs.getInt("quant");
						query2.get(i).m1_count++;
						
					}
					else if(rs.getInt("month")==2)
					{
						query2.get(i).M2_total+=rs.getInt("quant");
						query2.get(i).m2_count++;
					}
					else if(rs.getInt("month")==3)
					{
						query2.get(i).M3_total+=rs.getInt("quant");
						query2.get(i).m3_count++;
					}
					else if(rs.getInt("month")==4)
					{
						query2.get(i).M4_total+=rs.getInt("quant");
						query2.get(i).m4_count++;
					}
					else if(rs.getInt("month")==5)
					{
						query2.get(i).M5_total+=rs.getInt("quant");
						query2.get(i).m5_count++;
					}
					else if(rs.getInt("month")==6)
					{
						query2.get(i).M6_total+=rs.getInt("quant");
						query2.get(i).m6_count++;
					}
					else if(rs.getInt("month")==7)
					{
						query2.get(i).M7_total+=rs.getInt("quant");
						query2.get(i).m7_count++;
					}
					else if(rs.getInt("month")==8)
					{
						query2.get(i).M8_total+=rs.getInt("quant");
						query2.get(i).m8_count++;
					}
					else if(rs.getInt("month")==9)
					{
						query2.get(i).M9_total+=rs.getInt("quant");
						query2.get(i).m9_count++;
					}
					else if(rs.getInt("month")==10)
					{
						query2.get(i).M10_total+=rs.getInt("quant");
						query2.get(i).m10_count++;
					}
					else if(rs.getInt("month")==11)
					{
						query2.get(i).M11_total+=rs.getInt("quant");
						query2.get(i).m11_count++;
					}
					else
					{
						query2.get(i).M12_total+=rs.getInt("quant");
						query2.get(i).m12_count++;
					}
				}
				else
				{
					count++;
				}
			}
			if(count==query2.size())
			{ //create new object for comparing and then updating by adding.
				Report2 r2=new Report2();
				r2.custName=rs.getString("cust");
				r2.prodName=rs.getString("prod");
				if(rs.getInt("month")==1)
				{
					r2.M1_total=rs.getInt("quant");
					r2.m1_count=1;
				}
				else if(rs.getInt("month")==2)
				{
					r2.M2_total=rs.getInt("quant");
					r2.m2_count=1;
				}
				else if(rs.getInt("month")==3)
				{
					r2.M3_total=rs.getInt("quant");
					r2.m3_count=1;
				}
				else if(rs.getInt("month")==4)
				{
					r2.M4_total=rs.getInt("quant");
					r2.m4_count=1;
				}
				else if(rs.getInt("month")==5)
				{
					r2.M5_total=rs.getInt("quant");
					r2.m5_count=1;
				}
				else if(rs.getInt("month")==6)
				{
					r2.M6_total=rs.getInt("quant");
					r2.m6_count=1;
				}
				else if(rs.getInt("month")==7)
				{
					r2.M7_total=rs.getInt("quant");
					r2.m7_count=1;
				}
				else if(rs.getInt("month")==8)
				{
					r2.M8_total=rs.getInt("quant");
					r2.m8_count=1;
				}
				else if(rs.getInt("month")==9)
				{
					r2.M9_total=rs.getInt("quant");
					r2.m9_count=1;
				}
				else if(rs.getInt("month")==10)
				{
					r2.M10_total=rs.getInt("quant");
					r2.m10_count=1;
				}
				else if(rs.getInt("month")==11)
				{
					r2.M11_total=rs.getInt("quant");
					r2.m11_count=1;
				}
				else
				{
					r2.M12_total=rs.getInt("quant");
					r2.m12_count=1;
				}
				query2.add(r2);
			}
		}
		//displaying output
		System.out.println("CUSTOMER  PRODUCT   MONTH    BEFORE_AVG  AFTER_AVG");
		System.out.println("========  =======   =====    ==========  =========");
		
		for(Report2 m : query2)
		{
			//Before and after month are displayed respectively.
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"1","<Null>",m.getAvg(m.M2_total, m.m2_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"2",m.getAvg(m.M1_total, m.m1_count),m.getAvg(m.M3_total, m.m3_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"3",m.getAvg(m.M2_total, m.m2_count),m.getAvg(m.M4_total, m.m4_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"4",m.getAvg(m.M3_total, m.m3_count),m.getAvg(m.M5_total, m.m5_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"5",m.getAvg(m.M4_total, m.m4_count),m.getAvg(m.M6_total, m.m6_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"6",m.getAvg(m.M5_total, m.m5_count),m.getAvg(m.M7_total, m.m7_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"7",m.getAvg(m.M6_total, m.m6_count),m.getAvg(m.M8_total, m.m8_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"8",m.getAvg(m.M7_total, m.m7_count),m.getAvg(m.M9_total, m.m9_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"9",m.getAvg(m.M8_total, m.m8_count),m.getAvg(m.M10_total, m.m10_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"10",m.getAvg(m.M9_total, m.m9_count),m.getAvg(m.M11_total, m.m11_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"11",m.getAvg(m.M10_total, m.m10_count),m.getAvg(m.M12_total, m.m12_count));
				System.out.println();
				System.out.printf("%-10s%-10s%4s%17s%11s",m.custName,m.prodName,"12",m.getAvg(m.M11_total, m.m11_count),"<Null>");
				System.out.println();
			
		}
	} catch (SQLException e) {
		System.out.println("Username or Password incorrect!");
		e.printStackTrace();
	}
}
}
class Report2
{
	String custName="";
	String prodName="";
	int M1_total,M2_total,M3_total,M4_total,M5_total
	,M6_total,M7_total,M8_total,M9_total,M10_total
	,M11_total,M12_total;
	int m1_count,m2_count,m3_count
	,m4_count,m5_count,m6_count,m7_count,m8_count
	,m9_count,m10_count,m11_count,m12_count;
	//Calculating average as string
	public String getAvg(int total,int count)
	{
		if(count!=0)
		{
			int avg=total/count;
			return String.valueOf(avg);
		}
		return "<Null>";
	}
	
	
	
}

