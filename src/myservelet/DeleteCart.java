package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Cartlist;

/**
 * Servlet implementation class DeleteCart
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Connection connection=null;
		Statement sqlStatement=null;
		String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
		String str ="DELETE FROM cart WHERE id="+'"'+request.getParameter("cartID")+'"';
		String news="";
		try {
			connection=DriverManager.getConnection(url,"root","admin");
			sqlStatement=connection.createStatement();
			sqlStatement.executeUpdate(str);
//			PreparedStatement sql=connection.prepareStatement(str);
//			ResultSet result=(ResultSet) sql.executeQuery();
//			if (result.next()) {
//				news="删除成功";
//			}
			sqlStatement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("showCart");
		
		
	}

}
