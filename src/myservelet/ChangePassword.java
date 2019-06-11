package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.UserInfo;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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

	private void continueDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		String uname=userInfo.getUname();
		String password=request.getParameter("password");
		String passwordAgain=request.getParameter("passwordAgain");
		String passwordMessage="修改失败";
		if (password.equals(passwordAgain)) {
			try {
				String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
				Connection connection=DriverManager.getConnection(url,"root","admin");
				String str="UPDATE user SET upassword ="+"'"+password+"'"+" where uname="+"'"+uname+"'";
				PreparedStatement sql=connection.prepareStatement(str);
				int n=sql.executeUpdate();
				if (n!=0) {
					passwordMessage="修改成功";
				}
				sql.close();
				connection.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		request.setAttribute("passwordMessage", passwordMessage);
		RequestDispatcher dispatcher= request.getRequestDispatcher("password.jsp");
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
