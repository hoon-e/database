package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/***
 * 
 * @author jaehoon
 *  납품 관련 클래스
 *
 */
public class aboutSupply {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	//  생성자에서 DB 연결 수행
	public aboutSupply() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] list() {		
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = null;
			
			String search = "SELECT COUNT(*) AS COUNT FROM List;";
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT List_id FROM List;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("List_id"));
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
}
