package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.AdminInfo;
import mybean.data.UserInfo;

/**
 * Servlet implementation class HDadmin
 */
@WebServlet("/HDadmin")
public class HDadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HDadmin() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		AdminInfo adminInfo=null;
		HttpSession session=request.getSession();
		try {
			adminInfo=(AdminInfo) session.getAttribute("AdminInfo");
			if (adminInfo==null) {
				adminInfo=new AdminInfo();
				session.setAttribute("AdminInfo", adminInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			adminInfo=new AdminInfo();
			session.setAttribute("AdminInfo", adminInfo);
		}
		String aname=request.getParameter("aname");
		String apassword=request.getParameter("apassword");
//		String jjjString=request.getParameter("jjj");
		String backNews="";
		boolean statue=false;
		if (aname.length()==0||apassword.length()==0||aname==null||apassword==null) {
			backNews="请检查账号密码";
		}
		else {
			String url="jdbc:mysql://127.0.0.1:3306/shop";
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url,"root","admin");
				String condition="select * from `admin` where aname='"+aname+"'and apassword='"+apassword+"'";
				Statement sql=connection.createStatement();
				ResultSet resultSet=sql.executeQuery(condition);
				boolean m=resultSet.next();
				if (m) {
					backNews="登录成功";
					adminInfo.setAname(aname);
					adminInfo.setApassword(apassword);
					statue=true;
				}
				else {
					backNews="登录失败，检查用户名与密码，或者该账户不是管理员账户，你输入的用户名与密码是："+aname+"----"+apassword;
					
				}
				sql.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.setAttribute("adminCheckmess", backNews);
		if (statue) {
			RequestDispatcher dispatcher= request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher= request.getRequestDispatcher("admin_login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
