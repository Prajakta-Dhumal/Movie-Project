package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieModal 
{
    Connection con;
    {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movieproject?user=root&password=sql@123");
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public double getBill(String movieName, int no_of_ticket)
	{
		//DECLARE THE RESOURCES
		PreparedStatement pstmt;
		ResultSet rs;
		int db_no_of_ticket=0;
		double price_per_ticket=0;
		double total_amount=0;
		double finalAmount=0;
		int gst_Chargers=18;
		//WRITE THE QUERY
		String query="select no_of_tickets_available,price_per_ticket from movie_details where movie_name=?";
		String query1="update movie_details set no_of_tickets_available=? where movie_name=?";
		//CREATE A PLATFORM
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,movieName);
			
			
			//SET THE VALUE
			
			//EXECUTE THE QUERY
			rs=pstmt.executeQuery();
			if(rs.next()) 
			{
				db_no_of_ticket=rs.getInt(1);
				price_per_ticket=rs.getInt(2);
			
				pstmt=con.prepareStatement(query1);
				//set the value
				pstmt.setInt(1,db_no_of_ticket-no_of_ticket);
				pstmt.setString(2,movieName);
				pstmt.executeUpdate();
				
					total_amount=no_of_ticket*price_per_ticket;
					finalAmount=total_amount+gst_Chargers/total_amount*100;				}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalAmount;
	}

	public boolean checkAvailableTicket(String movieName, int no_of_ticket) 
	{
		  //DECLARE THE RESOURCES
				PreparedStatement pstmt;
				ResultSet rs;
				int db_no_of_ticket=0;
				boolean result=false;
	      //WRITE THE QUERY
				String query="select no_of_tickets_available from movie_details where movie_name=?";
				//CREATE A PLATFORM
				try {
					pstmt=con.prepareStatement(query);
					//SET THE VALUE
					pstmt.setString(1,movieName);
					//EXECUTE THE QUERY
					rs=pstmt.executeQuery();
					if(rs.next()) 
					{
						db_no_of_ticket=rs.getInt(1);
						
						if(no_of_ticket<=db_no_of_ticket) 
						{
							result=true;
						}
					}
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				return result;
		
	}
	

}
