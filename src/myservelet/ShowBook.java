package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.glass.ui.Application;

import mybean.data.BookInfo;

/**
 * Servlet implementation class ShowBook
 */
@WebServlet("/ShowBook")
public class ShowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBook() {
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
		Vector booklist=new Vector();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		Connection connection=null;
		Statement sqlStatement=null;
		String sql ="select * from book";
		try {
			String url="jdbc:mysql://127.0.0.1:3306/shop";
			connection=DriverManager.getConnection(url,"root","admin");
			sqlStatement=connection.createStatement();
			ResultSet result=(ResultSet) sqlStatement.executeQuery(sql);
			while (result.next()) {
				BookInfo bookInfo=new BookInfo();
				bookInfo.setBid(result.getString("Bid"));
				bookInfo.setBname(result.getString("Bname"));
				bookInfo.setBprice(result.getString("Bprice"));
				bookInfo.setBpic(result.getString("Bpic"));
				booklist.add(bookInfo);
			}
			connection.close();
			sqlStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		BookInfo book1=(BookInfo)booklist.elementAt(1);
		session.setAttribute("booklist", booklist);
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
