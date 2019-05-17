package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.CommonResponse;

//import beans.CommonResponse;
//import constants.DBNames;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		String sql ="select * from tb_product";

		System.out.println(sql);

		// 自定义的结果信息类
		CommonResponse res = new CommonResponse();
		try {
			ResultSet result = DBUtil.query(sql); // 数据库查询操作
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("rank", result.getString("rank"));
				map.put("name", result.getString("name"));
				map.put("time", result.getString("time"));
				map.put("likes", String.valueOf(result.getDouble("likes")));
				res.addListItem(map);
			}
			res.setResCode("0"); // 这个不能忘了，表示业务结果正确
		} catch (SQLException e) {
			res.setResult("300", "数据库查询错误");
			e.printStackTrace();
		}

		String resStr = JSONObject.fromObject(res).toString();
		response.getWriter().append(resStr).flush();
	}

}