package java_socket_login;

import java.sql.*;

public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tutorial = database 테이블 이름, root호스트 이름 , 비번
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_info","root","322031181216!");
			st = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 오류: "+e.getMessage());
		}
	}
	public boolean isAdmin(String adminID, String adminPassword) {
		try {
			String SQL = "SELECT * FROM ADMIN WHERE adminID = '"+adminID +"' and adminPassword = '" + adminPassword+"'";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e){
			System.out.println("데이터베이스 검색 오류: "+e.getMessage());
		}
		return false;
	}
}
