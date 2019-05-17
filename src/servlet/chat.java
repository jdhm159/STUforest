package servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(description = "发消息",urlPatterns = "/chat")
public class chat extends HttpServlet {



    public chat(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String code="";
        String message="";

        String content=request.getParameter("content");
        System.out.println("content:"+content);

        Connection connect = DBUtil.getConnect();

        try {

            Statement statement = connect.createStatement();

            String sql = "INSERT "+DBUtil.TABLE_CHAT_CONTENT+" (content) VALUE ('"+content+"');";

            System.out.println(sql);

            int result = statement.executeUpdate(sql);

            if (result>0) {

                code = "200";

                message = "插入成功";

            } else {



                code = "100";

                message = "插入不成功";

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }


        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append("code:").append(code).append(";message:").append(message);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        doGet(request,response);

    }
}
