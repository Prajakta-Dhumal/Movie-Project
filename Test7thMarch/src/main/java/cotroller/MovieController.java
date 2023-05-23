package cotroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MovieModal;
@WebServlet("/movielink")
public class MovieController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//FETCH THE DATA FROM HTML PAGE
		String movieName=req.getParameter("moviename");
		int no_of_ticket=Integer.parseInt(req.getParameter("ticket"));
		
		//CREATE THE OBJECT OF MOVIE CLASS
		MovieModal m=new MovieModal();
		boolean result=m.checkAvailableTicket(movieName,no_of_ticket);
		double totalAmount=0;
		if(result) 
		{
			//FIND THE FINAL BILL
		     totalAmount=m.getBill(movieName,no_of_ticket);
		     
		     //FORWARD THE REQUEST
		     RequestDispatcher rd=req.getRequestDispatcher("getbill.jsp");
		     req.setAttribute("finalAmount",totalAmount);
		     req.setAttribute("movieName",movieName );
		     
		     rd.forward(req, resp);
		    
		}
		else 
		{
			//FORWARD THE REQIEST TO HOUSFULL
			RequestDispatcher rd=req.getRequestDispatcher("housefull.jsp");
			rd.forward(req, resp);
			
		}
		
	
		  
	    
	}

}
