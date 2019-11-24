package aboutSign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SignUpCheck {
	private Connection con;
	private Statement stmt;
	PreparedStatement pstmt;
	
	public SignUpCheck() {
		try {
			System.out.println("회원가입 진입");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			System.out.println("Database Connect!");
			stmt = con.createStatement();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void signUP(int id, String password, String name, int age, String gender, int center) {
		String SQL = "INSERT INTO Customer VALUES (?,?,?,?,?,?);";
		try {
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2,  name);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setInt(5, center);
			pstmt.setString(6, password);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] list_center() {
		String[] result = null;
		try {
			String search = "SELECT COUNT(*) AS COUNT FROM Center;";
			ResultSet rs = null;
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			System.out.println(count);
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Center_id FROM Center;";
			rs = stmt.executeQuery(search2);

			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Center_id"));
				num += 1;
			}
			return result;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	// 중복확인 검사 함수
	public boolean find(int loginid) {
		try {
			String search = "SELECT * FROM Customer;";
			ResultSet rs = null;
			rs = stmt.executeQuery(search);
			
			// 중복 id가 있을 경우 false를 리턴한다.
			if(rs.next()) {
				if(rs.getInt("Customer_id") == loginid) {
					return false;
				}
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		// true 리턴
		return true;
	}
}
