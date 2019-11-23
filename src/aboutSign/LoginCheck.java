package aboutSign;

import java.sql.*;

public class LoginCheck {
	public boolean doLogin(int loginid, String loginpwd) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			Statement stmt = con.createStatement();
			String search = "SELECT * FROM Customer WHERE Customer_id=" + loginid + ";";
			ResultSet rs = null;
			rs = stmt.executeQuery(search);
			
			if(rs.next()) {
				if(loginpwd.equals(rs.getString("passwd"))) {
					return true;
				}
			}
			con.close();
		}catch(Exception e)
		{ 
			System.out.println(e);
		}
		return false;
	}
}
	