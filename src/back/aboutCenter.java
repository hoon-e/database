package back;

/**********************
 Center관련 MySQL 클래스
**********************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Center DB 값 가져오기
	public String[][] getValue() {
		try {
			String SQL = "SELECT * FROM Center;";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][4];
			
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("Center_id"));
				result[i][1] = rs.getString("Center_name");
				result[i][2] = rs.getString("Center_phone");
				result[i][3] = rs.getString("Center_address");
				i++;
			}
			
			con.close();
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Center id DB 값 가져오기
		public String[] getiDValue(int id) {
			try {
				String SQL = "SELECT * FROM Center WHERE Center_id=" + id + ";";
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				ResultSet rs = stmt.executeQuery(SQL);
				
				String[] result = new String[4];
				
				while(rs.next()){
					result[0] = Integer.toString(rs.getInt("Center_id"));
					result[1] = rs.getString("Center_name");
					result[2] = rs.getString("Center_phone");
					result[3] = rs.getString("Center_address");
				}
				
				con.close();
				return result;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	// 중복 검색
	public boolean isDuplicated(int id) {
		String SQL = "SELECT * FROM Center WHERE Center_id =" + id + ";";
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			ResultSet rs = stmt.executeQuery(SQL);
			
			if(rs.next()) {
				// 값이 존재한다면 TRUE를 리턴
				System.out.println("값이 존재합니다.");
				return true;
			}
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 센터 정보 추가
		public void editCenter(int id, String name, String phone, String address, int cid) {
			String SQL = "UPDATE Center SET Center_id=?, Center_name= ?, Center_phone= ?, Center_address=? WHERE Center_id=?";
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");

				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, id);
				pstmt.setString(2,  name);
				pstmt.setString(3, phone);
				pstmt.setString(4, address);
				pstmt.setInt(5, cid);
				
				// SQL문 쿼리 실행
				int row = pstmt.executeUpdate();
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	// 센터 정보 추가
	public void addCenter(int id, String name, String phone, String address) {
		String SQL = "INSERT INTO Center VALUES (?,?,?,?);";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");

			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2,  name);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
