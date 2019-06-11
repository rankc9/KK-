package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Address;
import mybean.data.UserInfo;

/**
 * Servlet implementation class ChangeAddress
 */
@WebServlet("/ChangeAddress")
public class ChangeAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		String uname=userInfo.getUname();
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String home=request.getParameter("home");
		try {
			String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
			Connection connection=DriverManager.getConnection(url,"root","admin");
			String str="UPDATE address SET name ="+"'"+name+"',"+"phone="+"'"+phone+"',"+"email="+"'"+email+"',"+"home="+"'"+home+"'"+" where uname="+"'"+uname+"'";
			PreparedStatement sql=connection.prepareStatement(str);
			int n=sql.executeUpdate();
			sql.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("address.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
