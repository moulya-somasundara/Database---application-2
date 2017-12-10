/*Name: Moulya Soma Sundara
 *CWID: 10415052
 *CS-561
 *Query-1
 * Algorithm:
 * Initialize an array list at the beginning which will store our values.
 * use of Array list will give us values in order unlike the hash tables.
 *  Calculate the average of customer-product_state sales.
 *  then update if the pair is not available.
 *  Then calculate average of other products for that customer.
 *  Also average of other products and customer combination for that particular state.
 *  then combine results and display group by customers and product.
 */

package sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Query1{
    static ArrayList <Report1> query1 = new ArrayList<Report1>();
    public static void main(String [] args)
    {
        String usr ="postgres";
        String pwd ="hanumanp";
        String url ="jdbc:postgresql://localhost:5432/postgres";
        try
        {
            Class.forName("org.postgresql.Driver");
            System.out.println("Success loading Driver!");
        }
        catch(Exception e)
        {
            System.out.println("Fail loading Driver!");
            e.printStackTrace();
        }
        try
        {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
            //while loop scans table row by row
            while (rs.next())
            {
                
                int count=0;
                int length=query1.size();
                for(int i=0;i<length;i++)
                {
                    //For calculating average of the customer-product-state sales
                    if(query1.get(i).customer.equals(rs.getString("cust")) && query1.get(i).product.equals(rs.getString("prod")) && query1.get(i).state.equals(rs.getString("state")))
                    {
                        query1.get(i).cust_avg+=rs.getInt("quant");
                        query1.get(i).count1++;
                    }
                    else
                    {count++;}
                }
                //If the customer-product_state pair is not available, add it to the result set
                if(count==query1.size())
                {
                    Report1 next=new Report1();
                    next.customer=rs.getString("cust");
                    next.product=rs.getString("prod");
                    next.state=rs.getString("state");
                    next.cust_avg=rs.getInt("quant");
                    next.count1=1;
                    query1.add(next);
                    
                }
                
            }
        }catch(SQLException e)
        {
            
        }
        //Calculate average of other products and customers
        for(int i=0;i<query1.size();i++)
        {
            for(int j=0;j<query1.size();j++)
            {
                //For customer average for other product
                if(!query1.get(i).customer.equals(query1.get(j).customer) && query1.get(i).product.equals(query1.get(j).product))
                {
                    query1.get(i).other_state_avg+=query1.get(j).cust_avg;
                    query1.get(i).count2+=query1.get(j).count1;
                }
                // For  customers and product  average for other state
                if(query1.get(i).product.equals(query1.get(j).product) && query1.get(i).customer.equals(query1.get(j).customer) && !query1.get(i).state.equals(query1.get(j).state))
                {
                    query1.get(i).other_prod_avg+=query1.get(j).cust_avg;
                    query1.get(i).count3+=query1.get(j).count1;
                }
            }
        }
        //displaying the output
        System.out.println("CUSTOMER  PRODUCT   STATE  CUST_AVG   OTHER_STATE_AVG  OTHER_PROD_AVG");
        System.out.println("========  ========== ======== ========  ==============  ==============");
        
        for(Report1 r1 : query1)
        {
            System.out.printf("%-10s%-10s%-10s%10d%16d%16d",r1.customer,r1.product,r1.state,r1.cust_avg/r1.count1,r1.other_state_avg/r1.count2,r1.other_prod_avg/r1.count3);
            System.out.println("");
        }
    }
}
//For creating object of each customer-product pair
class Report1
{
    String customer="";
    String product="";
    String state="";
    int cust_avg;
    int other_state_avg;
    int other_prod_avg;
    
    int count1;
    int count2;
    int count3;
    
    public int getCount1()
    {
        return this.count1;
    }
    public int getCount2()
    {
        return this.count2;
    }
    public int getCount3()
    {
        return this.count3;
    }
    
}
