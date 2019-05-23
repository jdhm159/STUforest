package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    // table

    public static final String TABLE_PASSWORD = "table_user_password";

    public static final String TABLE_USERINFO = "table_user_info";

    public static final String TABLE_CHAT_CONTENT="chat_content";


    // connect to MySql database

    public static Connection getConnect() {

        String url = "jdbc:mysql://localhost:3306/stu_forest?serverTimezone=UTC"; // ���ݿ��Url

        Connection connecter = null;

        try {

            Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��

            connecter = (Connection) DriverManager.getConnection(url, "root", "123456");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());

            System.out.println("SQLState: " + e.getSQLState());

            System.out.println("VendorError: " + e.getErrorCode());

        }

        return connecter;
    }
    
    ////////////////////////////////////////////

	/**
	 * 查询操作
	 * 
	 * @param querySql
	 *            查询操作SQL语句
	 * @return查询
	 * @throws SQLException
	 */
	public static ResultSet query(String querySql) throws SQLException {
		Statement stateMent = (Statement) getConnect().createStatement();
		return stateMent.executeQuery(querySql);
	}

	/**
	 * 插入、更新、删除操作
	 * 
	 * @param insertSql
	 *            插入操作的SQL语句
	 * @return
	 * @throws SQLException
	 */
	public static int update(String insertSql) throws SQLException {
		Statement stateMent = (Statement) getConnect().createStatement();
		return stateMent.executeUpdate(insertSql);
	}

    
}