package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class aboutEmployee {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	// Center list 반환
	public String[] list_center() {
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();

			String search = "SELECT COUNT(*) AS COUNT FROM Center;";
			ResultSet rs = null;
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Center_id FROM Center;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Center_id"));
				num += 1;
			}
			con.close();
			return result;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
		
	// Dep list 반환
	public String[] list_dep(int cid) {
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();

			String search = "SELECT COUNT(*) AS COUNT FROM Department WHERE Center_id=" + cid + ";";
			ResultSet rs = null;
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Dep_id FROM Department WHERE Center_id=" + cid + ";";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Dep_id"));
				num += 1;
			}
			con.close();
			return result;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
		
	//  생성자에서 DB 연결 수행
	public aboutEmployee() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
