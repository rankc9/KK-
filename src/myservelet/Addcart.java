package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.mysql.cj.Session;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.runtime.Name;

import mybean.data.UserInfo;

/**
 * Servlet implementation class Addcart
 */
@WebServlet("/Addcart")
public class Addcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addcart() {
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
		String Bookname=request.getParameter("bookname");
		String Bookid=request.getParameter("bookid");
		String Bookprice=request.getParameter("bookprice");
		try {
			String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
			String  string="select *  from cart where Cbookid="+Bookid+" and Cname="+'"'+userInfo.getUname()+'"';
			
			Connection connection=DriverManager.getConnection(url,"root","admin");
			PreparedStatement sql=connection.prepareStatement(string);
//			sql.setString(1, Bookid);
//			sql.setString(2, '"'+userInfo.getUname()+'"');
			ResultSet result=(ResultSet) sql.executeQuery();
			if (result.next()) {
				String str1="UPDATE cart set Cnumber=Cnumber+1,Cprice=Cprice+"+Bookprice+" WHERE Cbookid="+Bookid+" and Cname="+'"'+userInfo.getUname()+'"';
				sql=connection.prepareStatement(str1);
				int n=sql.executeUpdate();
			}
			else {
				
		    
				String str2="INSERT INTO cart VALUES "+"("+"'"+getOrderIdByUUId()+"'"+","+"'"+Bookname+"'"+","+"'"+Bookprice+"'"+","+"'"+"1"+"'"+","+"'"+Bookid+"'"+","+"'"+userInfo.getUname()+"'"+")";
				Statement sqlStatement1=connection.createStatement();
				int m=sqlStatement1.executeUpdate(str2);			
//				sql=connection.prepareStatement(str2);
//				sql.setString(1, Bookprice);
//				sql.setString(2, Bookid);
//				int m=sql.executeUpdate();
			}
			sql.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("index.jsp");
		
	}

	private  static String getOrderIdByUUId() {
		// TODO Auto-generated method stub
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {
			hashCodeV = - hashCodeV;
			}
		return machineId+ String.format("%015d", hashCodeV);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
