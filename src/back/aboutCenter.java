package back;

/**********************
 Center관련 MySQL 클래스
**********************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class aboutCenter {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	// 생성자에서 DB 연결 수행
	public aboutCenter() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			System.out.println("Center DB Connect!");
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 중복 검색
	public boolean isDuplicated(int id) {
		String SQL = "SELECT * FROM Center WHERE Center_id =" + id;
		try {
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.next()) {
				// 값이 존재한다면 TRUE를 리턴
				System.out.println("값이 존재합니다.");
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 센터 정보 추가
	public void addCenter(int id, String name, String phone, String address) {
		String SQL = "INSERT INTO Center VALUES (?,?,?,?,?);";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2,  name);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
