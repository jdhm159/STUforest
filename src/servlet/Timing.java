package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.sql.DataSource;

import org.apache.catalina.Context;




/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class Timing extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Timing() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	    String studytime= request.getParameter("studytime");
	    int id =1;
	  

        PrintWriter out = response.getWriter();
        System.out.println("studytime" + "," + studytime);
        try
        {
        	Connection con=DBUtil.getConnect();
        	Statement statement=con.createStatement();
		String sqlInsert="insert into " + "daytime" + "(studytime) values('"
	        	 + studytime + "')";
		


         

			System.out.println(sqlInsert);
			if (statement.executeUpdate(sqlInsert) > 0) { 
				out.println("上传成功");
				out.flush();
				out.close();
				System.out.println("成功");
			} else {
				out.println("上传失败");
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