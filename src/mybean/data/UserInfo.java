package mybean.data;

public class UserInfo {
	private String uname ,upassword ,backNews;
	private boolean statue=false;
	public boolean isStatue() {
		return statue;
	}
	public void setStatue(boolean statue) {
		this.statue = statue;
	}
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	

}
