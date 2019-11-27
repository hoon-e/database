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
	
	// Employee DB 값 가져오기
	public String[][] getValue(int did) {
		try {
			String SQL = "SELECT * FROM Employee WHERE Dep_id=" + did + ";";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][5];
			
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("Emp_id"));
				result[i][1] = rs.getString("Emp_name");
				result[i][2] = rs.getString("Emp_phone");
				result[i][3] = rs.getString("Emp_addr");
				result[i][4] = Integer.toString(rs.getInt("Emp_age"));
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
		
	// Duplicate Search
	public boolean isDuplicated(int id) {
		String SQL = "SELECT * FROM Employee WHERE Emp_id =" + id +";";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
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
		
	// 센터 정보 추가
		public void addEmp(int id, String name, String phone, String address, int age, int did) {
			String SQL = "INSERT INTO Employee VALUES (?,?,?,?,?,?);";
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, id);
				pstmt.setString(2,  name);
				pstmt.setString(3, phone);
				pstmt.setString(4, address);
				pstmt.setInt(5, age);
				pstmt.setInt(6, did);
				
				// SQL문 쿼리 실행
				int row = pstmt.executeUpdate();
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
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
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
