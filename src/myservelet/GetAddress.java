package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Address;
import mybean.data.Order;
import mybean.data.UserInfo;

/**
 * Servlet implementation class GetAddress
 */
@WebServlet("/GetAddress")
public class GetAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		if (userInfo==null) {
			userInfo=new UserInfo();
		}
		if(userInfo.isStatue()==true) {
			continueDoGet(request, response);
		}
		if (userInfo.isStatue()==false) {
			response.sendRedirect("login.jsp");
		}
	}

	private void continueDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		Address address=new Address();
		try {
			String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
			Connection connection=DriverManager.getConnection(url,"root","admin");
			String str="select * from `address` where uname="+'"'+userInfo.getUname()+'"';
			PreparedStatement sql=connection.prepareStatement(str);
			ResultSet result=(ResultSet) sql.executeQuery();
			while (result.next()) {
				address.setUname(result.getString("uname"));
				address.setName(result.getString("name"));
				address.setEmail(result.getString("email"));
				address.setPhone(result.getString("phone"));
				address.setHome(result.getString("home"));
			}
			connection.close();
			sql.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("Address", address);
		session.setAttribute("Aaddress", address);
		RequestDispatcher dispatcher= request.getRequestDispatcher("address.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
