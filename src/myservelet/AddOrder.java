package myservelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ietf.jgss.Oid;

import com.sun.glass.ui.Size;

import mybean.data.Cartlist;
import mybean.data.Order;
import mybean.data.UserInfo;

/**
 * Servlet implementation class AddOrder
 */
@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrder() {
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
		Vector Allcart=(Vector)session.getAttribute("Allcart");
		if (Allcart==null) {
			String Ordermessage="你的购物车没有图书，请先添加";
			RequestDispatcher dispatcher= request.getRequestDispatcher("showAddordermess.jsp");
			dispatcher.forward(request, response);
		}else {
			Order order=new Order();
			order.setOid(getOrderIdByUUId());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setOdate(df.format(new Date()));
			String Omessage="";
			double Ototal=0.00;
			String Oname="",Oaddress="",Ophone="";
			
			UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
			String Ousername=userInfo.getUname();
			for (int i = 0; i < Allcart.size(); i++) {
				Cartlist cartlist=(Cartlist)Allcart.elementAt(i);
				Omessage=Omessage+cartlist.getCbookname()+"X"+cartlist.getCnumber()+"/";
				Ototal=Ototal+Double.parseDouble(cartlist.getCprice());
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
			Connection connection=null;
			String url="jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8";
			Statement sqlStatement;
			Statement sqlStatement2;
			try {
				String sql1="select *  from address where uname="+"'"+Ousername+"'";
				connection=DriverManager.getConnection(url,"root","admin");
				sqlStatement=connection.createStatement();
				ResultSet resultSet=sqlStatement.executeQuery(sql1);
				if (resultSet.next()) {
					Oaddress=resultSet.getString("home");
					Ophone=resultSet.getString("phone");
					Oname=resultSet.getString("name");
				}
				order.setOmessage(Omessage);
				order.setOaddress(Oaddress);
				order.setOname(Oname);
				order.setOphone(Ophone);
				order.setOusername(Ousername);
				sqlStatement.close();
				String sql2="INSERT INTO `order` VALUES "+"("+"'"+order.getOid()+"'"+","+"'"+order.getOdate()+"'"+","+"'"+order.getOmessage()+"'"+","+"'"+Ototal+"'"+","+"'"+order.getOname()+"'"+","+"'"+order.getOaddress()+"'"+","+"'"+order.getOphone()+"'"+","+"'"+order.getOusername()+"'"+")";
				sqlStatement2=connection.createStatement();
				int m=sqlStatement2.executeUpdate(sql2);
				sqlStatement2.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("deleteAllCart");
			
		}
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
