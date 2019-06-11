package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.BookInfo;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
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
		HttpSession session=request.getSession();
		session.removeAttribute("booklist");
		Vector booklist=new Vector();
		
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
				bookInfo.setBid("Bid");
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
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
