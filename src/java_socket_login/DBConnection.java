package java_socket_login;

import java.sql.*;

public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tutorial = database ���̺� �̸�, rootȣ��Ʈ �̸� , ���
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_info","root","322031181216!");
			st = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����: "+e.getMessage());
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
			System.out.println("�����ͺ��̽� �˻� ����: "+e.getMessage());
		}
		return false;
	}
}
