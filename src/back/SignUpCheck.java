package back;

/**********************
  회원가입 관련 MySQL 클래스
**********************/

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
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	// 회원가입 함수
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
	
	// Center list 반환
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
	
	public String getName(int loginid) {
		try {
			String SQL = "SELECT Customer_name FROM Customer WHERE Customer_id = " + loginid + ";";
			ResultSet rs = null;
			rs = stmt.executeQuery(SQL);	
			if(rs.next()) {
				return rs.getString("Customer_name");
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "";
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
