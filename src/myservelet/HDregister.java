package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.Register;

/**
 * Servlet implementation class HDregister
 */
@WebServlet("/HDregister")
public class HDregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HDregister() {
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
		Register register=new Register();
		request.setAttribute("register", register);
		String uname= request.getParameter("uname").trim();
		String upassword=request.getParameter("upassword").trim();
		String name= request.getParameter("name").trim();
		String email=request.getParameter("email").trim();
		String phone=request.getParameter("phone").trim();
		String home=request.getParameter("home").trim();
		Connection connection=null;
		Statement sqlStatement=null;
		if (uname==null) {
			uname="";
		}
		if (upassword==null) {
			upassword="";
		}
		boolean status=true;
		for (int i = 0; i < uname.length(); i++) {
			char c=uname.charAt(i);
			if(!((c>'a'&&c<'z')||(c<'Z'&&c>'A')||(c>'0'&&c<'9'))) {
				status=false;
			}
		}
		boolean boo=uname.length()>0&&upassword.length()>0&&status;
		String backNews="";
		if (boo) {
			String s1="('"+uname+"','"+upassword+"')";
			String s2="('"+uname+"','"+name+"','"+phone+"','"+email+"','"+home+"')";
			String url="jdbc:mysql://127.0.0.1:3306/shop";
			try {
				connection=DriverManager.getConnection(url,"root","admin");
				String insertCondition1="INSERT INTO user VALUES "+s1;  
				sqlStatement=connection.createStatement();
				 int m=sqlStatement.executeUpdate(insertCondition1);
				 if (m!=0) {
					 backNews="注册成功";
					 register.setBackNews(backNews);
					 register.setUname(uname);
					 register.setUpassword(upassword);
					 register.setBackNews(backNews);
					 
					 String insertCondition2="INSERT INTO address VALUES "+s2;
					 int n=sqlStatement.executeUpdate(insertCondition2);
					 if (n!=0) {
						 register.setName(name);
						 register.setPhone(phone);
						 register.setEmail(email);
						 register.setHome(home);
					}
				}
				 else {
					backNews="名字已经有人使用，请重新注册";
					register.setBackNews(backNews);
					
				}
				connection.close();
				sqlStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				backNews="名字已经有人使用，请重新注册";
				register.setBackNews(backNews);
			} 
		}
		else {
			backNews="注册使用非法字符";
			register.setBackNews(backNews);
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("showRegisterMessage.jsp");
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
