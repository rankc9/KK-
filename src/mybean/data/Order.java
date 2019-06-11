package mybean.data;

public class Order {
	private String Oid,Odate,Omessage,Ototal,Oname,Oaddress,Ophone,Ousername;
	public String getOusername() {
		return Ousername;
	}
	public void setOusername(String ouname) {
		Ousername = ouname;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public String getOid() {
		return Oid;
	}
	public void setOid(String oid) {
		Oid = oid;
	}
	public String getOdate() {
		return Odate;
	}
	public void setOdate(String odate) {
		Odate = odate;
	}
	public String getOmessage() {
		return Omessage;
	}
	public void setOmessage(String omessage) {
		Omessage = omessage;
	}
	public String getOtotal() {
		return Ototal;
	}
	public void setOtotal(String ototal) {
		Ototal = ototal;
	}
	public String getOname() {
		return Oname;
	}
	public void setOname(String oname) {
		Oname = oname;
	}
	public String getOaddress() {
		return Oaddress;
	}
	public void setOaddress(String oaddress) {
		Oaddress = oaddress;
	}
	public String getOphone() {
		return Ophone;
	}
	public void setOphone(String ophone) {
		this.Ophone = ophone;
	}
	
}
