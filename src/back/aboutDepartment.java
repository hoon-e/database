package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class aboutDepartment {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	// 생성자에서 DB 연결 수행
	public aboutDepartment() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Department DB 값 가져오기
	public String[][] getValue() {
		try {
			String SQL = "SELECT * FROM Department;";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][5];
			
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("Dep_id"));
				result[i][1] = rs.getString("Dep_name");
				result[i][2] = rs.getString("Dep_phone");
				result[i][3] = rs.getString("Dep_address");
				result[i][4] = rs.getString("Center_id");
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
	
	// Department id DB 값 가져오기
		public String[] getiDValue(int id) {
			try {
				String SQL = "SELECT * FROM Department WHERE Dep_id=" + id + ";";
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				ResultSet rs = stmt.executeQuery(SQL);
				
				String[] result = new String[5];
				
				while(rs.next()){
					result[0] = Integer.toString(rs.getInt("Dep_id"));
					result[1] = rs.getString("Dep_name");
					result[2] = rs.getString("Dep_phone");
					result[3] = rs.getString("Dep_address");
					result[4] = Integer.toString(rs.getInt("Center_id"));
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
		String SQL = "SELECT * FROM Department WHERE Dep_id =" + id;
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
	
	// 부서 정보 수정
	public void editDep(int id, String name, String phone, String address, int cid, int did) {
		String SQL = "UPDATE Department SET Dep_id=?, Dep_name= ?, Dep_phone= ?, Dep_address=?, Center_id=? WHERE Dep_id=?";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");

			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2,  name);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setInt(5, cid);
			pstmt.setInt(6, did);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	// 부서 정보 추가
	public void addDep(int id, String name, String phone, String address, int centerid) {
		String SQL = "INSERT INTO Department VALUES (?,?,?,?,?);";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");

			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setInt(5, centerid);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
