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

import mybean.data.AdminInfo;
import mybean.data.Order;
import mybean.data.UserInfo;

/**
 * Servlet implementation class ShowAllorder
 */
@WebServlet("/ShowAllorder")
public class ShowAllorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllorder() {
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
//		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		AdminInfo adminInfo=(AdminInfo)session.getAttribute("AdminInfo");
		if (adminInfo==null) {
			response.sendRedirect("admin_login.jsp");
		}
		else if(adminInfo.getAname()!=null){
			continueDoGet(request, response);
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
//		AdminInfo adminInfo=(AdminInfo)session.getAttribute("AdminInfo");
		Vector Allorder=new Vector();
		try {
			String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
			Connection connection=DriverManager.getConnection(url,"root","admin");
			String str;
//			if (adminInfo.getAname()!=null) {
				str="select * from `order` ";
//			}
			
//			str="select * from `order` where Ousername="+'"'+userInfo.getUname()+'"';

			PreparedStatement sql=connection.prepareStatement(str);
			ResultSet result=(ResultSet) sql.executeQuery();
			while (result.next()) {
				Order order=new Order();
				order.setOid(result.getString("Oid"));
				order.setOdate(result.getString("Odate"));
				order.setOmessage(result.getString("Omessage"));
				order.setOtotal(result.getString("Ototal"));
				order.setOname(result.getString("Oname"));
				order.setOaddress(result.getString("Oaddress"));
				order.setOphone(result.getString("Ophone"));
				order.setOusername(result.getString("Ousername"));
				Allorder.add(order);
			}
			connection.close();
			sql.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("Allorder", Allorder);
		session.setAttribute("Allorder", Allorder);
//		if (adminInfo.getAname()!=null) {
//			RequestDispatcher dispatcher= request.getRequestDispatcher("Allorder.jsp");
//			dispatcher.forward(request, response);
//		}

			RequestDispatcher dispatcher= request.getRequestDispatcher("Allorder.jsp");
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
