package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.sql.*;

@WebServlet("/Fabu")
public class Fabu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Fabu() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
		    String Name= request.getParameter("Name");
	        String User= request.getParameter("User");
	        String Phone= request.getParameter("Phone");
	        String Place= request.getParameter("Place");
	        PrintWriter out = response.getWriter();
	        try
	        {
	        	Connection con=DBUtil.getConnect();
	        	Statement statement=con.createStatement();
			String sqlInsert="insert into " + "wuping" + "(Name,User,Phone,Place) values('"
	        	+ Name + "', '" + User + "','" + Phone + "', '" + Place +"')";

				System.out.println(sqlInsert);
				if (statement.executeUpdate(sqlInsert) > 0) { 
					out.println("发布成功");
					out.flush();
					out.close();
					System.out.println("成功");
				} else {
					out.println("发布失败");
					out.flush();
					out.close();
					System.out.println("失败");
				}
		

	        }
	        catch(SQLException e)
	        {
	        	e.printStackTrace();
	        }
	        finally
	        {
	        	out.flush();
	        	out.close();
	        }
	}
}