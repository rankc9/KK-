package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.scene.control.SplitPane.Divider;
import mybean.data.UserInfo;

/**
 * Servlet implementation class HDlogin
 */
@WebServlet("/HDlogin")
public class HDlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HDlogin() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserInfo userInfo=null;
		HttpSession session=request.getSession();
		try {
			userInfo=(UserInfo) session.getAttribute("userInfo");
			if (userInfo==null) {
				userInfo=new UserInfo();
				session.setAttribute("userInfo", userInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			userInfo=new UserInfo();
			session.setAttribute("userInfo", userInfo);
		}
		String uname=request.getParameter("uname");
		String upassword=request.getParameter("upassword");
//		String jjjString=request.getParameter("jjj");
		boolean statue=userInfo.isStatue();
		String backNews="";
		if (statue&&uname.equals(userInfo.getUname())) {
			backNews="你已经登录此账号";
			userInfo.setBackNews(backNews);
		}
		else if (uname.length()==0||upassword.length()==0||uname==null||upassword==null) {
			backNews="请检查账号密码";
			userInfo.setBackNews(backNews);
		}
		else {
			String url="jdbc:mysql://127.0.0.1:3306/shop";
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url,"root","admin");
				String condition="select * from user where uname='"+uname+"'and upassword='"+upassword+"'";
				Statement sql=connection.createStatement();
				ResultSet resultSet=sql.executeQuery(condition);
				boolean m=resultSet.next();
				if (m) {
					backNews="登录成功";
					userInfo.setUname(uname);
					userInfo.setUpassword(upassword);
					userInfo.setBackNews(backNews);
					userInfo.setStatue(true);
				}
				else {
					backNews="登录失败，检查用户名与密码，你输入的用户名与密码是："+uname+"----"+upassword;
					userInfo.setUname("游客");
					userInfo.setBackNews(backNews);
					userInfo.setStatue(false);
				}
				sql.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if (userInfo.isStatue()) {
			RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("loginmessage", backNews);
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
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
